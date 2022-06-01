package com.retailer.rewards.controller;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retailer.rewards.model.Customer;

@Repository
public interface CustRepo extends JpaRepository<Customer, Integer> {

	// customers with their transactions for given month and year
	@Query(value = "Select distinct c from Customer c JOIN FETCH c.purchaseDetails tr where MONTH(tr.purchaseDate) = :month AND YEAR(tr.purchaseDate) = :year")
	public List<Customer> monthSummary(@Param("month") int month, @Param("year") int year);

	// customers with their transactions for given window of Dates
	@Query(value = "Select distinct c from Customer c JOIN FETCH c.purchaseDetails tr where tr.purchaseDate BETWEEN :start AND :end   ")
	public List<Customer> summaryOfMonths(@Param("start") Date start, @Param("end") Date end);
	
	
}