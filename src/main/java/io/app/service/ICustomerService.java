package io.app.service;

import java.util.List;

import io.app.dto.Customer;

public interface ICustomerService {

	public Customer saveCustomer(Customer customer);
	public List<Customer> getAllCustomers();
	public Customer getCustomer(long accountNumber);
}
