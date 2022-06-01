package com.retailer.rewards.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.rewards.model.Customer;

@Service
public class RewardsService {

	@Autowired
	private CustRepo custRepo;

	// Customers and their transactions
	public List<Customer> getCustomerAll() {
		return custRepo.findAll();
	}

	// Customers and their transactions by ID
	public Customer getCustomerById(Integer id) {

		return custRepo.findById(id).orElse(null);
	}

	// Customers and their transactions for given month and year
	public List<Customer> getMonthSummary(int month, int year) {
		return custRepo.monthSummary(month, year);
	}

	// Customers and their transactions for past three months
	public List<Customer> pastSummary() {

		// Current Date as end date
		Date end = Calendar.getInstance().getTime();

		Calendar cal = Calendar.getInstance();
		cal.setTime(end);
		// Back-dating for 3 months
		cal.add(Calendar.MONTH, -3);

		Date start = cal.getTime();

		return custRepo.summaryOfMonths(start, end);

	}
	
	
	

}
