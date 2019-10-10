package io.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferController {

	public OfferController() {
		
	}
		
	@GetMapping("/offers")
	public String getOffers() {
		return "offers";
	}

}
