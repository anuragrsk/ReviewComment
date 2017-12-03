package com.test.review.db.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity 
@Table (name="PRODUCT_CATALOG")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long catalog_id;
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,mappedBy = "catalog_id")
	@Where(clause=" review_status='Complete'")
	
	private List<ReviewCommentsEntiry> comments;
	@Column(name="CATALOG_DESC")
	private String name;
	@Column(name="NUMBER_USER")
	private long number_user;//
	private double review_score;
	private String status;
	@Column(name="SUM_REVIEW")
	private double sum_review;
	@OneToMany(mappedBy = "catalog_id", cascade = CascadeType.ALL  ,fetch=FetchType.LAZY)
	@Where(clause=" status='ACTIVE'")
	private List<ProductCategoryMappingEntity> categories;
	public List<ProductCategoryMappingEntity> getCategories() {
		return categories;
	}
	public void setCategories(List<ProductCategoryMappingEntity> categories) {
		this.categories = categories;
	}
	public void setCatalog_id(Long catalog_id) {
		this.catalog_id = catalog_id;
	}
	public long getCatalog_id() {
		return catalog_id;
	}
	public List<ReviewCommentsEntiry> getComments() {
		return comments;
	}
	public String getName() {
		return name;
	}
	
	public long getNumber_user() {
		return number_user;
	}
	
	public double getReview_score() {
		return review_score;
	}
	public String getStatus() {
		return status;
	}

	public double getSum_review() {
		return sum_review;
	}
	
	public void setCatalog_id(long catalog_id) {
		this.catalog_id = catalog_id;
	}
    public void setComments(List<ReviewCommentsEntiry> comments) {
		this.comments = comments;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNumber_user(long number_user) {
		this.number_user = number_user;
	}
	
	public void setReview_score(double review_score) {
		this.review_score = review_score;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setSum_review(double sum_review) {
		this.sum_review = sum_review;
	}
	@Override
	public String toString() {
		return "ProductEntity [number_user=" + number_user + ", review_score=" + review_score + ", sum_review="
				+ sum_review + "]";
	}
	

}
