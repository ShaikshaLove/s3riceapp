package io.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.app.util.TransactionUtil;

@Controller
public class WelcomeController {
	
	@Autowired
	private TransactionUtil transactionUtil;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(ModelMap map) {
			System.out.println(transactionUtil.getUiDynamics());
		return "Welcome";
	}
	@RequestMapping(value="/contact-us",method=RequestMethod.GET)
	public String contactUs() {
		return "Contact-Us";
	}
	

}
