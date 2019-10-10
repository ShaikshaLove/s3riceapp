package io.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.app.dto.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
