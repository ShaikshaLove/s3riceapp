package io.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.app.dto.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
