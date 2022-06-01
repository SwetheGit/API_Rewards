package com.retailer.rewards.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Integer id;
	private String customer_name;
	
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Purchase> purchaseDetails;
	
	
	@JsonInclude
	@Transient
	private Long rewardTotalPoints;
	@JsonInclude
	@Transient
	private Double totalPurchases;
	
		
	public Customer() {
		super();
	}
	public Customer(Integer id, String customer_name) {
		super();
		this.id = id;
		this.customer_name = customer_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	public Set<Purchase> getPurchaseDetails() {
		return purchaseDetails;
	}
	public void setPurchaseDetails(Set<Purchase> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}
	
	
	//Fetching the Total Reward Points
	public Long getRewardTotalPoints() {
		if (purchaseDetails == null || purchaseDetails.isEmpty()) return 0l;
		
		return purchaseDetails.stream().map(a -> a.getrewardPoints().intValue()).reduce(0, (x,y) -> x + y).longValue();
	}
	
	//Fetching the Total Purchase Points
	public Double getTotalPurchases() {
		if (purchaseDetails == null || purchaseDetails.isEmpty()) return 0d;
		
		return purchaseDetails.stream().map(a -> a.getpurchaseAmount().doubleValue()).reduce(0d, (x,y) -> x + y).doubleValue();
	}
	
	
}
