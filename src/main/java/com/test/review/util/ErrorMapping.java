package com.test.review.util;

public enum ErrorMapping {

	INVALID_EMAIL(1, "Please enter a valid email."), 
	INVALID_COMMENTS(2, "Please enter comments."), 
	INVALID_RATING(3,
			"Please provide valid rating.Please provide value between 0 and 5."), 
	NO_COMMENTS_ENTERED(4,"Please enter comments. No comments were entered."),
	NO_PRODUCT_FOUND(5,"WE are not able to find product.This product is not valid or discontinued"),
	ERROR(90000,
					"We are unable to process your request. Please try after some time.")
	;
	private final int errorCode;
	private final String description;

	public int getErrorCode() {
		return errorCode;
	}

	public String getDescription() {
		return description;
	}

	private ErrorMapping(int code, String desc) {
		this.errorCode = code;
		this.description = desc;
	}

}
