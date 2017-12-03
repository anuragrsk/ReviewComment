package com.test.review.util;

public enum ReviewStatus {
	PENDING("Pending"),ACTIVE("Active"),REVIEW("Review");
	
	String status;
	private ReviewStatus(String reviewStaus) {
		this.status=reviewStaus;
	}
	public String getStatus() {
		return status;
	}
	
}
