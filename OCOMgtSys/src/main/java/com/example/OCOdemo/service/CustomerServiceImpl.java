package com.example.OCOdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.OCOdemo.model.Customer;
import com.example.OCOdemo.model.CustomerSignUp;
import com.example.OCOdemo.repository.CustomerRepoSignUp;
import com.example.OCOdemo.repository.CustomerRepository;

@Service

public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerRepoSignUp customerRepoSignUp;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}


	@Override
	public void saveCustomer(Customer customer) {
		this.customerRepository.save(customer);
		
	}


	@Override
	public Customer updateCustomer(Customer customer) {
		Customer existCustomer = customerRepository.findById(customer.getId()).get();
		existCustomer.setChickenName(customer.getChickenName());
		existCustomer.setPrice(customer.getPrice());
		existCustomer.setType(customer.getType());
		existCustomer.setQuantity(customer.getQuantity());
		existCustomer.setDays(customer.getDays());
		return customerRepository.save(existCustomer);
	}


	@Override
	public CustomerSignUp createAccount(CustomerSignUp SignUp) {
		return customerRepoSignUp.save(SignUp);
	}


	@Override
	public Page<Customer> getPaginated(int pageNo, int pageSize) {
		  Pageable pageable= PageRequest.of(pageNo-1,pageSize);
		return this.customerRepository.findAll(pageable);
	}

}
