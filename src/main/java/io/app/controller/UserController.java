package io.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	public UserController() {
	}
	
	
	@GetMapping("/otp-login")
	public String pageForOtpLogin() {
		return "otp-login";
	}

}
