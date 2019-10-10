package io.app.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.app.dto.Account;
import io.app.event.OnAccountCreationCompleteEvent;
import io.app.service.IAccountService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	private Logger logger=Logger.getLogger(AccountController.class);
	
	@RequestMapping(value="/account-creation",method=RequestMethod.GET)
	public String getAccountCreationForm(ModelMap map) {
		logger.info("Account - Creation form is sent to the client");
		map.put("account", new Account());
		return "Customer-Register";
	}
	
	@RequestMapping(value="/saveCustomer",method=RequestMethod.POST)
	public String saveustomer(@ModelAttribute Account account,ModelMap map) {
		
		Account accountCreated=accountService.createAccount(account);
		applicationEventPublisher.publishEvent(new OnAccountCreationCompleteEvent(accountCreated));
		map.put("message","The account has been created with account number "+ accountCreated.getAccountNumber());
		map.put("account", new Account());
		logger.info("The Account has been created for a new user "+account.getCustomer().getFirstName());
		return "Customer-Register";
	}

	@RequestMapping(value="/deleteAccount",method=RequestMethod.GET)
	public String deleteAccount(@RequestParam("accountNumber")long accountNumber, ModelMap map) {
		logger.info("Account deletion started");

		accountService.deleteAccountDetail(accountNumber);
		map.put("message","The Account number"+accountNumber+" details has been deleted ");
		return "redirect:/getAllCustomers?message="+" The Account number "+accountNumber+" details has been deleted ";
	}
}
