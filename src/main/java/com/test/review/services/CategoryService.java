package com.test.review.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.review.db.entity.ProductCategoryEntity;
import com.test.review.db.repository.CategoryCRUDInterface;
import com.test.review.model.Category;
import com.test.review.rest.elements.response.CategoryResponse;

@Service
public class CategoryService {
	private static final Logger logger=Logger.getLogger(CategoryService.class);
	
/**
 * 
 * @param categoryCrud
 * @param req
 * @param response
 * @return
 */
	public List<Category> getCategories( Pageable req, CategoryResponse response, CategoryCRUDInterface categoryCrud) {
		List<Category> categories = Collections.emptyList();
		if (categoryCrud == null) {
			logger.debug("CatalogService:End ProductCRUDInterface is null retruning empty.");
			return categories;
		}
		Page<ProductCategoryEntity> it = categoryCrud.findAll(req);
		response.setTotalPages(it.getTotalPages());
		Category cat = null;
		categories=new ArrayList<Category>();
		
		for (ProductCategoryEntity catalog : it) {
			cat = new Category();
			BeanUtils.copyProperties(catalog, cat);
			categories.add(cat);
		}
		logger.debug("Retruning Size:"+categories.size());
		return categories;
	}

}
