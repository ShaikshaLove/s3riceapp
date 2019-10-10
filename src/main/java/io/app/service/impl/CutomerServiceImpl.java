package io.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.app.dto.Customer;
import io.app.repository.CustomerRepository;
import io.app.service.ICustomerService;
@Service
public class CutomerServiceImpl implements ICustomerService {
	
	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return repository.findAll();
	}

	@Override
	public Customer getCustomer(long accountNumber) {
    	return repository.getOne(accountNumber);
	}

}
