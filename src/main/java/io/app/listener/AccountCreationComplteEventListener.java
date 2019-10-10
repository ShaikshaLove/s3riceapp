package io.app.listener;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import io.app.dto.Account;
import io.app.event.OnAccountCreationCompleteEvent;
@Component
public class AccountCreationComplteEventListener implements ApplicationListener<OnAccountCreationCompleteEvent> {
	
	@Autowired
	private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;
    
	private Logger logger=Logger.getLogger(AccountCreationComplteEventListener.class);

	@Override
	public void onApplicationEvent(OnAccountCreationCompleteEvent event) {
      Account account=event.getAccount();
      Map<String,Object> model=new HashMap<>();
      model.put("firstName", account.getCustomer().getFirstName().toLowerCase());
      model.put("lastName", account.getCustomer().getLastName().toLowerCase());
      model.put("accountNumber", ""+account.getAccountNumber());
      model.put("signature", "Mohammad basha");
      model.put("location", "Tadipatri, Andhrapradesh");
      
      
      MimeMessage message = javaMailSender.createMimeMessage();
      try {
		MimeMessageHelper helper = new MimeMessageHelper(message,
		          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
		          StandardCharsets.UTF_8.name());
        // helper.addAttachment("logo.png", new ClassPathResource("s3rice.jpg"));

		   final Context context = new Context();
	        context.setVariables(model);
	        
	        
	       final String html =templateEngine.process("account-email-template", context);
            
	        helper.setTo(account.getCustomer().getEmail());
	        helper.setText(html,true);
	        helper.setSubject("S3rice Account information");
		    helper.setFrom("shaiksha.sayyad1@gmail.com");
		    javaMailSender.send(message);
		    logger.info("A mail has been sent");
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      
      
	}

}
