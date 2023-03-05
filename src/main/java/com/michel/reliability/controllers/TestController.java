package com.michel.reliability.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michel.reliability.service.statisics.TestService;

@RestController
public class TestController {
	
	@Autowired
	TestService testService;
	
	@GetMapping("/test1")
	public void test1() {
		
		testService.activer();
		
	}
	
	@GetMapping("/test3")
	public void test3() {
		
		testService.activer3();
		
	}
	
	@GetMapping("/reg")
	void regression() {
		
		testService.regression();
		
	}
	
	@GetMapping("/coef")
	void coefficients() {
		
		testService.regCoefficients();
	}
		
		
		
	
	
	
	
	

}
