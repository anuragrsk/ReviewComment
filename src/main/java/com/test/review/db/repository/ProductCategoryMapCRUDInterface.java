package com.test.review.db.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.test.review.db.entity.ProductCategoryMappingEntity;

@Component
@CacheConfig(cacheNames = "PRODUCT_CATEGORY_MAPPING")
public interface ProductCategoryMapCRUDInterface extends CrudRepository<ProductCategoryMappingEntity, Long>{

}
