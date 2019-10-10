package io.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.app.dto.Account;
import io.app.dto.Customer;
import io.app.exception.WrongAccountNumberException;
import io.app.service.IAccountService;
import io.app.service.ICustomerService;

@Controller
public class CustomerController {
	@Autowired
	private IAccountService accountService;

	@Autowired
	private ICustomerService customerService;

	@GetMapping("/account-page")
	public String getAccountCreationPage(ModelMap map) {
		map.put("cust", new Customer());
		return "Customer-Register";
	}

	@RequestMapping(value="/saveCust",method=RequestMethod.POST)
	public String saveustomer(@ModelAttribute Customer customer,ModelMap map) {
		map.put("message","The account has been created with account number "+ customerService.saveCustomer(customer).getCustomerId());
		map.put("cust", new Customer());
		return "Customer-Register";
	}

	@RequestMapping(value="/getAllCustomers",method=RequestMethod.GET)
	public  String getAllCustomers(@RequestParam(value="message",required = false)String message,ModelMap map){
		if(message!=null) {
			map.put("message", message);
		}
		map.put("customerList", customerService.getAllCustomers());
		return "Customer-list";
	}
	
	
	@RequestMapping(value="/payNow",method=RequestMethod.GET)
	public  String getCheckDuePage(){
       return "PayNow";
	}
	
	
	@RequestMapping(value="/getCustomer",method=RequestMethod.POST)
	public  String getCustomerDetail(@RequestParam("accountNumber")long accountNumber,ModelMap map){
		map.put("customer", customerService.getCustomer(accountNumber));
       return "PayNow";
	}
	
	@RequestMapping("/due-details")
	public String checkDuePage() {
		return "CheckDue";
	}
	@RequestMapping(value="/checkTheDue",method=RequestMethod.POST)
	public String checkTheDue(@RequestParam("accountNumber")long accountNumber,ModelMap map)throws WrongAccountNumberException {
		 Account account=accountService.getAccountDetail(accountNumber);
		 if(account==null) {
		    throw new WrongAccountNumberException("Account Number is not found");	 
		 }
		 map.put("account",account);
		 return "CheckDue";
	}
	
	@RequestMapping(value="/getAccountDetails",method=RequestMethod.GET)
	public  String getAccountDetail(@RequestParam("accountNumber")long accountNumber,ModelMap map){
		map.put("account", accountService.getAccountDetail(accountNumber));
       return "PayNow";
	}
	
}