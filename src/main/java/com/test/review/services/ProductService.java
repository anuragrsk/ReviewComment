package com.test.review.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.review.db.entity.ProductEntity;
import com.test.review.db.entity.ReviewCommentsEntiry;
import com.test.review.db.repository.ProductCRUDInterface;
import com.test.review.exception.ReviewCommentsException;
import com.test.review.model.Product;
import com.test.review.model.ReviewComment;
import com.test.review.rest.elements.response.ProductsResponse;
import com.test.review.util.ErrorMapping;

@Service
public class ProductService {
	private static final Logger logger=Logger.getLogger(ProductService.class);

	/**
	 * Get Catalog list 
	 * @param product
	 * @param req 
	 * @param response 
	 * @return
	 */
	public List<Product> getProducts( Pageable req, ProductsResponse response,long categoryId,ProductCRUDInterface productRepo) {

		List<Product> products = Collections.emptyList();
		if (productRepo == null) {
			logger.debug( "ProductService:End  ProductCRUDInterface is null retruning empty::");
			return products;
		}
		Page<ProductEntity> it = productRepo.findAll(req,categoryId);
		response.setTotalPages(it.getTotalPages());
		Product cat = null;
		products=new ArrayList<Product>();
		
		for (ProductEntity catalog : it) {
			cat = new Product();
			BeanUtils.copyProperties(catalog, cat);
			products.add(cat);
		}
		logger.debug(" :End retruning size::"
				+products.size());
		return products;
	}
	/**
	 * Copy review comments
	 * @param comments
	 * @param cat
	 */
	private void copyComments(List<ReviewCommentsEntiry> comments, Product cat) {
		ArrayList<ReviewComment> list=new ArrayList<ReviewComment>();
		ReviewComment request=null;
		for(ReviewCommentsEntiry review:comments) {
			 request=new ReviewComment();
			BeanUtils.copyProperties(review, request);
			list.add(request);
		}
		cat.setReviewComments(list);
	}
/**
 * 
 * @param curd
 * @param response 
 * @param id
 * @return
 * @throws ReviewCommentsException 
 */
	public Product getProduct( long id,ProductCRUDInterface productRepo) throws ReviewCommentsException {
		Product product = new Product();
		if (productRepo == null) {
			logger.debug("CatalogService:End ProductCRUDInterface is null retruning empty::");
			return product;
		}
		ProductEntity productEntity = productRepo.findActiveById(id);
		if(productEntity==null) {
			logger.debug("productEntity is null");
			throw new ReviewCommentsException(ErrorMapping.NO_PRODUCT_FOUND.getDescription(),ErrorMapping.NO_PRODUCT_FOUND.getErrorCode());
		}
	
		BeanUtils.copyProperties(productEntity, product);
		copyComments(productEntity.getComments(),product);
		logger.debug("End retruning size::" + product.getDecs());
		return product;
	}

}
