package com.test.review.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table (name="PRODUCT_CAT_MAPPING")
public class ProductCategoryMappingEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long mapping_id;
	
	private long catalog_id;
	private long cat_id;
	@Column(name="STATUS")
	private String map_status;
	public long getMapping_id() {
		return mapping_id;
	}
	public void setMapping_id(long mapping_id) {
		this.mapping_id = mapping_id;
	}
	
	public long getCatalog_id() {
		return catalog_id;
	}
	public void setCatalog_id(long catalog_id) {
		this.catalog_id = catalog_id;
	}
	public long getCat_id() {
		return cat_id;
	}
	public void setCat_id(long cat_id) {
		this.cat_id = cat_id;
	}
	public String getMap_status() {
		return map_status;
	}
	public void setMap_status(String map_status) {
		this.map_status = map_status;
	}
	


}
