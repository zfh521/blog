package in.meile.account.service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailSender implements Runnable {
	private String body;
	private String subject;
	private JavaMailSenderImpl javaMailSender;
	private String to;
    public EmailSender(String body,String subject,String to,JavaMailSenderImpl javaMailSender) {
		// TODO Auto-generated constructor stub
    	this.body=body;
    	this.subject=subject;
    	this.to=to;
    	this.javaMailSender=javaMailSender;
	}
	@Override
	public void run() {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		   MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
		
		    helper.getEncoding();
		    helper.setFrom(javaMailSender.getUsername());
		    helper.setTo(to);
		    helper.setSubject(subject);
		    //第二个参数true，表示text的内容为html，然后注意<img/>标签，src='cid:file'，'cid'是contentId的缩写，'file'是一个标记，需要在后面的代码中调用MimeMessageHelper的addInline方法替代成文件
		    helper.setText(
		    		body,
		            true);
		    javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
