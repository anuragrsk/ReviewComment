package com.test.review.model;

import java.util.ArrayList;

public final class Product{
	
	private long catalog_id;
	private String decs;
	private String name;
	private double review_score;
	private String status;
	private ArrayList<ReviewComment> reviewComments;
	
	public ArrayList<ReviewComment> getReviewComments() {
		return reviewComments;
	}
	public void setReviewComments(ArrayList<ReviewComment> reviewComments) {
		this.reviewComments = reviewComments;
	}
	public long getCatalog_id() {
		return catalog_id;
	}
	public String getDecs() {
		return decs;
	}
	public String getName() {
		return name;
	}
	public double getReview_score() {
		return review_score;
	}
	
	public String getStatus() {
		return status;
	}
	public void setCatalog_id(long catalog_id) {
		this.catalog_id = catalog_id;
	}
	public void setDecs(String decs) {
		this.decs = decs;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setReview_score(double review_score) {
		this.review_score = review_score;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
