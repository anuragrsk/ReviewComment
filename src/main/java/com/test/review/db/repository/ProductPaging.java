package com.test.review.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.test.review.db.entity.ProductEntity;

public interface ProductPaging extends PagingAndSortingRepository<ProductEntity, Long> {

}
