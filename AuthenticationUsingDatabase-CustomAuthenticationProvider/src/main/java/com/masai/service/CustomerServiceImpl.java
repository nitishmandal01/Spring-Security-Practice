package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.Exception.CustomerException;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;

public class CustomerServiceImpl implements com.masai.service.CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer registerCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerDetailsByEmail(String email) throws CustomerException {
//		Optional<Customer> opt = customerRepository.findByEmail(email);
//		if(opt.isPresent()) {
//			return opt.get();
//		}else {
//			throw new  CustomerException("Customer Not found with Email: "+email);
//		}

		return customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer Not found with Email: "+email));

	}

	@Override
	public List<Customer> getAllCustomerDetails() throws CustomerException {
		List<Customer> customers = customerRepository.findAll();
		
		if(customers.isEmpty()) {
			throw new CustomerException("NO Customer Found");
		}
		
		return customers;
	}

}
