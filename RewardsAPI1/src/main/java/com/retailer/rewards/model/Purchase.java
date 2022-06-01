package com.retailer.rewards.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Purchase extends Reward{
	@Id
	@GeneratedValue
	private Long id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn //foreign key to customer
	private Customer customer;
	private Double purchaseAmount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	
    public static int firstRewardLimit = 50;
    public static int secondRewardLimit = 100;
	
	
	public Purchase() {
		super();
	}
	public Purchase(Long id, Customer customer, Double purchaseAmount, Date date) {
		super();
		this.id = id;
		this.customer = customer;
		this.purchaseAmount = purchaseAmount;
		
		this.purchaseDate = date;
	}

	public Date getpurchaseDate() {
		return purchaseDate;
	}
	public void setpurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Double getpurchaseAmount() {
		return purchaseAmount;
	}
	public void setpurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	

	@Override
	public Long getrewardPoints() {
		this.rewards = 0l;
		
		if (this.purchaseAmount > firstRewardLimit && this.purchaseAmount <= secondRewardLimit) {
			this.rewards += (this.purchaseAmount.intValue() - firstRewardLimit) * 1;
			
		} 
		
		if (this.purchaseAmount > secondRewardLimit) {
			this.rewards += firstRewardLimit;                                           
			this.rewards += (this.purchaseAmount.intValue() - secondRewardLimit) * 2;  
		}
		
		return this.rewards;
	}
	
	@Override
	public String toString() {
		return String.format("Purchase [id=%s, customer=%s, purchaseAmount=%s,  purchaseDate=%s]", id, customer,
				purchaseAmount,  purchaseDate);
	}
	
	
	
}
