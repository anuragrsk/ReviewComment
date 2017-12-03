package com.test.review.db.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.test.review.db.entity.ProductCategoryEntity;
@Component
@CacheConfig(cacheNames = "PRODUCT_CATEGORY")
public interface CategoryCRUDInterface extends CrudRepository<ProductCategoryEntity, Long>{
	@Query("SELECT C FROM ProductCategoryEntity C ")
	List<ProductCategoryEntity> findAll();
	@Query(value="SELECT C FROM ProductCategoryEntity C ",nativeQuery=false)
	Page<ProductCategoryEntity> findAll(Pageable pageRequest);
}
