package com.retailer.rewards.model;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

public abstract class Reward {

	@JsonInclude  
	@Transient    
	protected Long rewards;

	public abstract Long getrewardPoints();

}
