package io.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.app.dto.Product;
import io.app.service.IProductService;

@Controller
public class ProductController {
	@Autowired
	private IProductService productService;
	
	@RequestMapping(value="/regProduct",method=RequestMethod.GET)
	public String getProductRegistrationPage(ModelMap map) {
		map.put("product", new Product());
		return "ProductRegister";
	}
	@RequestMapping(value="/saveProduct",method=RequestMethod.POST)
	public String createProduct(@ModelAttribute Product product,ModelMap map) {
		map.put("message","The product details has been saved with id "+productService.createProduct(product).getProductId());
		map.put("product", new Product());
		return "ProductRegister";
	}
	
	@RequestMapping(value="/getAllProducts")
	public String getAllProducts(ModelMap map) {
		map.put("products",productService.getAllProducts());
		return "ProductList";

	}
}
