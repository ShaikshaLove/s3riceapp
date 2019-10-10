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
import io.app.dto.Transaction;
import io.app.event.OnTransactionComplete;
@Component
public class TransactionCompleteListener implements ApplicationListener<OnTransactionComplete> {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Override
	public void onApplicationEvent(OnTransactionComplete event) {

		Logger logger=Logger.getLogger(TransactionCompleteListener.class);
		logger.info("Transaction complete listener is invoked ");

		Transaction trx=event.getTransaction();
		Account account=trx.getAccount();
		Map<String,Object> model=new HashMap<>();

		model.put("firstName", account.getCustomer().getFirstName().toLowerCase());
		model.put("lastName", account.getCustomer().getLastName().toLowerCase());
		model.put("accountNumber", ""+account.getAccountNumber());
		model.put("signature", "Mohammad basha");
		model.put("location", "Tadipatri, Andhrapradesh");
		model.put("trxId", trx.getTransactionId());
		model.put("trxAmount", trx.getTransactionAmount());
		model.put("brand","S3rice pvt ltd");
		model.put("theDueBeforePayment", account.getTheTotalDue()+trx.getTransactionAmount());
		model.put("theDueAfterPayment",trx.getTheDueAfterPayment());
		model.put("trxDate", trx.getTransactionDate());


		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// helper.addAttachment("logo.png", new ClassPathResource("s3rice.jpg"));

			final Context context = new Context();
			context.setVariables(model);


			final String html =templateEngine.process("transaction-complete-email", context);

			helper.setTo(account.getCustomer().getEmail());
			helper.setText(html,true);
			helper.setSubject("Voila!! The Transacton Is Successful.");
			helper.setFrom("shaiksha.sayyad1@gmail.com");
			javaMailSender.send(message);
			logger.info("A mail has been sent");

		}catch (Exception e) {
           logger.error(e);
		}
		
	}
}
