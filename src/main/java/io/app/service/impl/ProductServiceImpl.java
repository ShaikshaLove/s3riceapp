package io.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.app.dto.Product;
import io.app.repository.ProductRepository;
import io.app.service.IProductService;
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductRepository productRepository;
	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(int productId) {
		return productRepository.getOne(productId);
	}

}
