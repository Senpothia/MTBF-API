package com.michel.reliability.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.michel.reliability.models.Table;
import com.michel.reliability.service.statisics.TestService;
import com.michel.reliability.utils.DataFileManager;

@RestController
public class TestController {

	@Autowired
	TestService testService;

	/*
	 * Fonctionnel Génére un graphe png et et graphe pdf dans le dossier plots
	 * 
	 */
	@GetMapping("/test1")
	public void test1() {

		testService.activer();

	}

	// Fonctionnel - Tracé graphe Cairo avec script
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

	@GetMapping("/source")
	void printSourceCmde() {

		testService.printSource();
	}

	@GetMapping("/reg4")
	void regression4() {

		testService.regression4();

	}
	
	@GetMapping("/reg31")
	void regression31() {

		testService.regression31();

	}

	@GetMapping("/v3er/histo")
	void regression41() {

		testService.hist();

	}
	
	
	@GetMapping("/v3er/quant")
	void regression42() {

		testService.quantiles();

	}
	
	@GetMapping("/v3er/density")
	void regression43() {

		testService.density();

	}
	
	
	@GetMapping("/v3er/atypiques")
	void regression44() {

		testService.atypiques();

	}
	
	

	@PostMapping("/save/table2")
	void getTable2(@RequestBody String table) {
		System.out.println("requête reçue");
		System.out.println("table: " + table);
		DataFileManager.saveTable(table);

	}

}
