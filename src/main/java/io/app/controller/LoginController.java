package io.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import io.app.util.TransactionUtil;
@Controller
public class LoginController {
	
	@Autowired
	private TransactionUtil transactionUtil;
	
	 @RequestMapping("/login")
	  public String login() {
	    return "login";
	  }

	  // Login form with error
	  @RequestMapping("/login-error.html")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login.html";
	  }
	  
	  @RequestMapping("/dashboard")
	  public String dashboard(ModelMap model) {
	          model.addAttribute("trxList", transactionUtil.getUiDynamics());
	    return "Admin-dashboard.html";
	  }

}
