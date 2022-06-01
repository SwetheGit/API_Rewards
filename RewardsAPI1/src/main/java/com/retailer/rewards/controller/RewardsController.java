package com.retailer.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.rewards.model.Customer;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	//Fetch All Customer details	
	@GetMapping("/customers")
	public List<Customer> findCustomerAll() {
		return rewardsService.getCustomerAll();
	}

  //Fetch Customer Id details	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {

		Customer customer = rewardsService.getCustomerById(id);
		if (customer == null)
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// Fetch summary of a month; month and year sent as query params
	@GetMapping("/summary")
	public ResponseEntity<?> getMonthSummary(@RequestParam(name = "month", required = true) Integer month,
			@RequestParam(name = "year", required = true) Integer year) {

		if (month <= 1 || month >= 12) {
			return new ResponseEntity<>("Not a valid month", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Customer>>(rewardsService.getMonthSummary(month, year), HttpStatus.OK);
	}

	// Fetch past three months summary
	@GetMapping("/threeMonthsSummary")
	public ResponseEntity<List<Customer>> getMonthSummary() {
	return new ResponseEntity<List<Customer>>(rewardsService.pastSummary(), HttpStatus.OK);
	}
}
