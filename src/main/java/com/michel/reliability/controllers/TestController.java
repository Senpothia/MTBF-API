package com.michel.reliability.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michel.reliability.service.statisics.TestService;

@RestController
public class TestController {
	
	@Autowired
	TestService testService;
	
	
	/* Fonctionnel
	 * Génére un graphe png et et graphe pdf dans le dossier plots
	 * 
	*/
	@GetMapping("/test1")
	public void test1() {
		
		testService.activer();
		
	}
	
	// Fonctionnel  - Tracé graphe Cairo avec script
	@GetMapping("/test3")
	public void test3() {
		
		testService.activer21();
		
	}
	
	
	@GetMapping("/reg")
	void regression() {
		
		testService.regression2();
		
	}
	
	@GetMapping("/reg3")
	void regression2() {
		
		testService.regression3();
		
	}
	
	@GetMapping("/coef")
	void coefficients() {
		
		testService.regCoefficients();
	}
		
		
		
	
	
	
	
	

}
