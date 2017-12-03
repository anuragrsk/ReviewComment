
package com.test.review.db.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.test.review.db.entity.ProductEntity;
@Component
@CacheConfig(cacheNames = "PRODUCT_CATALOG")
public interface ProductCRUDInterface extends CrudRepository<ProductEntity, Long>{
	@Cacheable
//	@Query("SELECT CATALOG_ID,CATALOG_DESC,REVIEW_SCORE FROM PRODUCT_CATALOG ")
	List<ProductEntity> findAll();
	@Query(value="SELECT C FROM ProductEntity C where C.status='ACTIVE'",nativeQuery=false)
	List<ProductEntity> findActiveAll();
	
	@Query(value="SELECT C FROM ProductEntity C where C.status='ACTIVE' and C.catalog_id=?",nativeQuery=false)
	ProductEntity findActiveById(long catalog_id);
	@Query(value="SELECT C FROM ProductEntity C where C.status='ACTIVE'",nativeQuery=false)
	Page<ProductEntity> findAll(Pageable pageRequest);
	
	@Query(value="SELECT C FROM ProductEntity C inner join   C.categories  E       where     E.map_status='ACTIVE' and  C.status='ACTIVE' and E.cat_id=:catId",nativeQuery=false)
	Page<ProductEntity> findAll(Pageable req, @Param("catId")long categoryId);

}
