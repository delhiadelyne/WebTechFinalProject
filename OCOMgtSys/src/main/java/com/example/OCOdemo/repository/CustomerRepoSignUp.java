package com.example.OCOdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OCOdemo.model.CustomerSignUp;

public interface CustomerRepoSignUp extends JpaRepository<CustomerSignUp, Integer>{
	
	CustomerSignUp findByTelphone(String telphone);

}
