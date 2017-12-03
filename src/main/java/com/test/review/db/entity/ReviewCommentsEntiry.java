package com.test.review.db.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name="REVIEW_COMMENTS")
public class ReviewCommentsEntiry {
	private long catalog_id;
	private String created_by;
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private long review_comment_id;
	private String review_comments;
	private double review_score;
	private String review_status;
	private String reviewer_email;
	private String reviewer_name;
	private Date modified_on=new Date();
	public Date getCreated_on() {
		return modified_on;
	}
	public void setCreated_on(Date created_on) {
		this.modified_on = created_on;
	}
	public long getCatalog_id() {
		return catalog_id;
	}
	public String getCreated_by() {
		return created_by;
	}
	
	public long getReview_comment_id() {
		return review_comment_id;
	}
	public String getReview_comments() {
		return review_comments;
	}
	public double getReview_score() {
		return review_score;
	}
	public String getReview_status() {
		return review_status;
	}
	public String getReviewer_email() {
		return reviewer_email;
	}
	public String getReviewer_name() {
		return reviewer_name;
	}
	
	public void setCatalog_id(long catalog_id) {
		this.catalog_id = catalog_id;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	
	public void setReview_comment_id(long review_comment_id) {
		this.review_comment_id = review_comment_id;
	}
	public void setReview_comments(String review_comments) {
		this.review_comments = review_comments;
	}
	public void setReview_score(double review_score) {
		this.review_score = review_score;
	}
	public void setReview_status(String review_status) {
		this.review_status = review_status;
	}
	public void setReviewer_email(String reviewer_email) {
		this.reviewer_email = reviewer_email;
	}
	public void setReviewer_name(String reviewer_name) {
		this.reviewer_name = reviewer_name;
	}
	

}
