package com.example.OCOdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.OCOdemo.model.Customer;
import com.example.OCOdemo.model.CustomerSignUp;

public interface CustomerService {
    List<Customer> getAllCustomers();
    void saveCustomer (Customer customer);
    
    public Customer updateCustomer(Customer customer);
    
    public CustomerSignUp createAccount(CustomerSignUp SignUp);
    
    public Page<Customer> getPaginated(int pageNo,int pageSize);
    
}
