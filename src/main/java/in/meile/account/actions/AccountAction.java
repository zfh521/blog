package in.meile.account.actions;

import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import in.meile.account.domain.Account;
import in.meile.account.service.AccountService;
import in.meile.account.service.UserService;

@Controller
public class AccountAction {
 @Resource
 private AccountService accountService;
	
  @RequestMapping("/sayHello")
  @ResponseBody
  public String sayHellp(){
	  return "123";
  }
  @RequestMapping("/account/reg")
  @ResponseBody
  public Map<String,Object> register(Account account){
	 Map<String,Object> result=Maps.newHashMap();
	 
	 try {
		 Map<String,Object> params=Maps.newHashMap();
		 params.put("email", account.getEmail());
		 Account account1=accountService.getByEmail(account.getEmail());
		 if(account1!=null){
			 result.put("status", 0);
			 result.put("message", "邮箱不可用");
			 
		 }else{
			 accountService.addAccount(account);
			 result.put("status", 1);
			 result.put("contents", account);
		 }
		 
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		result.put("status", 0);
		result.put("message", e.getMessage());
	}

	 return result;
  }
  @RequestMapping("/account/get")
  @ResponseBody
  public Account getAccount(String email){
	  try {
		  Account account = accountService.getByEmail(email);
		  return account;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return null;
  }
  @RequestMapping("/account/active")
  public String active(String email,String code){
	  Account account=new Account();
	  account.setEmail(email);
	  try{
		  Map<String,Object> params=Maps.newHashMap();
		  params.put("email", email);
		  account= accountService.getByEmail(email);
			 if(StringUtils.equals(account.getPassword(), code)&&account.getType()==1){
				 accountService.active(account);
				return "redirect:/checkResult.html?msg="+URLEncoder.encode("账户激活成功","UTF-8") +"&status=0";
			 }else{
				 return "redirect:/checkResult.html?msg="+URLEncoder.encode("账户激活错误","UTF-8") +"&status=1";
			 }
	  }catch(Exception e){
		  e.printStackTrace();
		  return "redirect:/checkResult.html?msg="+URLEncoder.encode("账户激活错误") +"&status=1";
	  }
  }
 
}
