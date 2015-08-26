package in.meile.account.service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import in.meile.account.domain.Account;
import in.meile.account.mapper.AccountMapper;

@Service("accountService")
@Transactional("isap")
public class AccountService {
	@Resource
   private AccountMapper mapper;
	@Resource
   private JavaMailSenderImpl javaMailSender;
	
   public Account getById(Long id){
	   in.meile.account.mapper.AccountMapper mapper1;
	  return  mapper.get(id);
   }
   public void addAccount(Account account){
	  // Sha1Hash.
	   String salt=getSalt(9);
	   String password=encodePassword(account.getPassword(), salt);
	   account.setPassword(password);
	   account.setSalt(salt);
	   mapper.insert(account);
	    try {
		   onAccountAdd(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
   public void onAccountAdd(Account account) throws MessagingException{
	   Map<String, Object> root = new HashMap<String,Object>();
	   Map<String,Object> params= Maps.newHashMap();
	   
	   params.put("accountName",account.getName());
	   params.put("url", "http://meile.in/account/active?email="+account.getEmail()+"&code="+account.getPassword());
	   root.put("params", params);
	  String body= getContent("account-active.html",root);
	 EmailSender snder = new EmailSender(body, "美乐印-账户激活", account.getEmail(), javaMailSender);
	 new Thread(snder).start();
   }
   private String getContent(String template,Map<String,Object> model){
	   Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		 try {
			cfg.setDirectoryForTemplateLoading(new File(this.getClass().getResource("/").getPath()+ "/mailtemplates"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 cfg.setDefaultEncoding("UTF-8");

		 // Sets how errors will appear.
		 cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		 StringWriter wr=new StringWriter();
		 try {
			Template temp = cfg.getTemplate(template);
			
			temp.process(model, wr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return wr.toString();
   }
   public Account getByEmail(String email){
	   return mapper.getByMail(email);
   }
   public Account getByName(Account account){
	   return mapper.getByName(account);
   }
   private String encodePassword(String password,String salt){
	   password=salt+new Sha1Hash(password).toHex();
	   password=salt+new Sha1Hash(password).toHex();
	   password=new Sha1Hash(password).toHex();
	   return password;
   }
   private String getSalt(int length){
	   String temp="abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   int total=temp.length();
	   StringBuilder br=new StringBuilder();
	   for (int i = 0; i < length; i++) {
		  int j= (int)(Math.round(Math.random()*(total-1))) ;
		  
		  br.append(temp.charAt(j));
	   }
	   return br.toString();
   }
	public void active(Account account) {
		// TODO Auto-generated method stub
		account.setStatus(0);
		mapper.updateAccountStatus(account);
		try{
			onActive(account);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void onActive(Account account){
		 Map<String, Object> root = new HashMap<String,Object>();
		 root.put("account",account);
	  String body= getContent("account-notify.html",root);
	 EmailSender snder = new EmailSender(body, "美乐印-账户激活", account.getEmail(), javaMailSender);
	 new Thread(snder).start();
	}
}
