package com.test.review.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Result {
	private long number_user;
	private double sum_review;
	private long catalog_id;
	public Result(long number_user, double sum_review, long catalog_id) {
		super();
		this.number_user = number_user;
		this.sum_review = sum_review;
		this.catalog_id = catalog_id;
	}
	public long getNumber_user() {
		return number_user;
	}
	public void setNumber_user(long number_user) {
		this.number_user = number_user;
	}
	public double getSum_review() {
		return sum_review;
	}
	public void setSum_review(double sum_review) {
		this.sum_review = sum_review;
	}
	public long getCatalog_id() {
		return catalog_id;
	}
	public void setCatalog_id(long catalog_id) {
		this.catalog_id = catalog_id;
	}
	
}
