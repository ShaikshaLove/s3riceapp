package io.app.listener;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
import io.app.dto.PurchaseOrder;
import io.app.event.OnPurchaseOrderCreationEvent;
@Component
public class PurchaseOrderCreationListener implements ApplicationListener<OnPurchaseOrderCreationEvent> {
	@Autowired
	private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;
    
	private Logger logger=Logger.getLogger(PurchaseOrderCreationListener.class);

	@Override
	public void onApplicationEvent(OnPurchaseOrderCreationEvent event) {
          PurchaseOrder purchaseOrder=event.getPurchaseOrder();
          Account account=purchaseOrder.getAccount();
		  Map<String,Object> model=new HashMap<>();
	      model.put("firstName", account.getCustomer().getFirstName().toLowerCase());
	      model.put("lastName", account.getCustomer().getLastName().toLowerCase());
	      model.put("accountNumber", ""+account.getAccountNumber());
	      model.put("signature", "Mohammad basha");
	      model.put("location", "Tadipatri, Andhrapradesh");
	      model.put("purchaseOrder", purchaseOrder);


	      
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// helper.addAttachment("logo.png", new ClassPathResource("s3rice.jpg"));

			final Context context = new Context();
			context.setVariables(model);


			final String html =templateEngine.process("purchase-order-conformation-email", context);

			helper.setTo(account.getCustomer().getEmail());
			helper.setText(html,true);
			helper.setSubject("The Order Has Been Conformed And Delivered");
			helper.setFrom("shaiksha.sayyad1@gmail.com");
			javaMailSender.send(message);
			logger.info("A mail has been sent");

		}catch (Exception e) {
           logger.error(e);
		}
		
		
		
		
	}

}
