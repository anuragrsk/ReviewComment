package com.test.review.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.test.review.db.entity.ProductCategoryEntity;


public interface CategoryPageInterface  extends PagingAndSortingRepository<ProductCategoryEntity, Long>{
	
}
