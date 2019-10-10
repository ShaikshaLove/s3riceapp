package io.app.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.app.dto.Product;
import io.app.service.IProductService;

@Component
public class OrderUtil {
	@Autowired
	private  IProductService productService;
	public   List<Product> getDynamics(){
		return productService.getAllProducts();
	}

}
