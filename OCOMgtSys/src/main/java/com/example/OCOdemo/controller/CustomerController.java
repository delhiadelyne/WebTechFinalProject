package com.example.OCOdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.OCOdemo.model.Customer;
import com.example.OCOdemo.model.CustomerSignUp;
import com.example.OCOdemo.repository.CustomerRepoSignUp;
import com.example.OCOdemo.repository.CustomerRepository;
import com.example.OCOdemo.service.CustomerService;


@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerRepoSignUp customerlogin;

	
	/*@GetMapping("/viewslist")
	public String viewHomePage(Model model) {
		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return "index"; 
	}*/
	
	@GetMapping("/viewslist")
	public  ModelAndView homePagination() {
		return PageShow(1);
	}
	
	@GetMapping("/delete/{id}")
	public String showNewCustomerForm(@PathVariable("id") Integer id) {
		 Customer customer = customerRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		 customerRepository.delete(customer);
	        return "redirect:/viewslist";

	}
	
	@GetMapping("/addnewcustomers")
	public String showNewHome(Model model) {
		return "LoginForm";
	}
	
	@GetMapping("/")
	public String loginHome() {
		return "Login";
	}
	
	@GetMapping("/signupcustomers")
	public String signupHome() {
		return "signupcustomer";
	}
	
	
	 @PostMapping("/saveCustomer")
	 public String homeAddint(@RequestParam("Chickename") String Chickename, @RequestParam("price") String price, @RequestParam("type") String type ,@RequestParam("quantity") String quantity, @RequestParam("days") String days){
         Customer customer=new Customer();
         customer.setChickenName(Chickename);
         customer.setPrice(price);
         customer.setType(type);
         customer.setQuantity(quantity);
         customer.setDays(days);
         customerService.saveCustomer(customer);
		 return "redirect:/viewslist";
    }
	 
	 
	 
	 @GetMapping("/updates/{id}")
	    public ModelAndView showUpdateForm(@PathVariable("id") Integer id) {
	        Optional<Customer> customer = customerRepository.findById(id);
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName("new_customer");
	        mav.addObject("customer",customer);
	        return mav;
	   }
	 
	    @PostMapping("/update/{id}")
	    public String updateCustomers(@PathVariable("id") String id,Customer customer) {
	        Customer newCustomersinfo=new Customer();
	        newCustomersinfo.setId(customer.getId());
	        newCustomersinfo.setChickenName(customer.getChickenName());
	        newCustomersinfo.setPrice(customer.getPrice());
	        newCustomersinfo.setType(customer.getType());
	        newCustomersinfo.setQuantity(customer.getQuantity());
	        newCustomersinfo.setDays(customer.getDays());
	        customerService.updateCustomer(newCustomersinfo);
	        return "redirect:/viewslist";
	    }
	    
	    
	    @PostMapping("/account")
	    public String createAccount(@RequestParam("fname") String fname, @RequestParam("lname") String lname,@RequestParam("telephone") String telephone, @RequestParam("district") String district, @RequestParam("sector") String sector, @RequestParam("cell") String cell, @RequestParam("email") String email, @RequestParam("psw") String psw) {	       
	    	CustomerSignUp customerSignUp=new CustomerSignUp();
	    	customerSignUp.setFirstName(fname);
	    	customerSignUp.setLastname(lname);
	    	customerSignUp.setTelphone(telephone);
	    	customerSignUp.setDistrict(district);
	    	customerSignUp.setSector(sector);
	    	customerSignUp.setCell(cell);
	    	customerSignUp.setEmail(email);
	    	customerSignUp.setPassword(psw);
	    	
	    	customerService.createAccount(customerSignUp);
	        return "redirect:/";
	    }
	    
	    @PostMapping("/logincustomer")
	    public String loginAccount(@RequestParam("telephone") String telephone, @RequestParam("psw") String psw) {	       
	    	CustomerSignUp customerloginData=null;
	    	try {
	    		customerloginData=customerlogin.findByTelphone(telephone);
	    	}catch(Exception e) {
	    		System.out.println(e);
	    	}
	    	if(customerloginData!=null && customerloginData.getTelphone().equals(telephone)&&customerloginData.getPassword().equals(psw)) {
	    	 return "redirect:/viewslist";
	    	}else {
	    		return "redirect:/";
	    	}
	    }
	    
	    @GetMapping("/page/{pageNo}")
	    public  ModelAndView PageShow(@PathVariable (value = "pageNo") int pageNo){
	        ModelAndView mav=new ModelAndView();
	        int pageSize=5;
	        Page<Customer> page=customerService.getPaginated(pageNo,pageSize);
	        List<Customer> allusersdata=page.getContent();
	        mav.setViewName("index");
	        mav.addObject("currentPage",pageNo);
	        mav.addObject("totalPages",page.getTotalPages());
	        mav.addObject("totalItems",page.getTotalElements());
	        mav.addObject("listCustomers",allusersdata);
	        return  mav;
	    }
	    
	    
	        

}
