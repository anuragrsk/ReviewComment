package com.test.review.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name="PRODUCT_CATEGORY")
public class ProductCategoryEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CAT_ID")
	private long cat_id;
	@Column(name="CAT_DESC")
	private String cat_desc;
	@Column(name="STATUS")
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getCat_id() {
		return cat_id;
	}
	public void setCat_id(long cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_desc() {
		return cat_desc;
	}
	public void setCat_desc(String cat_desc) {
		this.cat_desc = cat_desc;
	}
	

}
