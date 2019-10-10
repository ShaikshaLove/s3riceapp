package io.app.service;

import java.util.List;

import io.app.dto.Product;

public interface IProductService {
   public Product createProduct(Product product);
   public List<Product> getAllProducts();
   public Product getProduct(int productId);
}
