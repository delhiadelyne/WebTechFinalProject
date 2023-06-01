package com.example.OCOdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "chickeName")
    private String chickenName;
	
	@Column(name = "price")
    private String price;
	
	@Column(name = "type")
    private String type;
	
	@Column(name = "quantity")
    private String quantity;
	
	@Column(name = "days")
    private String days;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChickenName() {
		return chickenName;
	}

	public void setChickenName(String chickenName) {
		this.chickenName = chickenName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}
	
	
}