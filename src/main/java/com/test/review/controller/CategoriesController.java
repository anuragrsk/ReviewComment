package com.test.review.controller;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.test.review.db.repository.CategoryCRUDInterface;
import com.test.review.model.Category;
import com.test.review.rest.elements.response.BaseResponse;
import com.test.review.rest.elements.response.CategoryResponse;
import com.test.review.services.CategoryService;
import com.test.review.util.ErrorMapping;

@Controller
public class CategoriesController {
	
	private static final Logger logger=Logger.getLogger(CategoriesController.class);
	@Autowired
	private CategoryService categoryService;
	
	public BaseResponse getCategories( Pageable req, CategoryCRUDInterface categoryRepo) {
		
		CategoryResponse response = new CategoryResponse();
		response.setSucess(true);
		List<Category> list=Collections.emptyList();
		try {
			list = categoryService.getCategories(req,response, categoryRepo);	
		}catch (DataAccessException ee) {
			logger.error("DataBase Exception", ee);
			response.setSucess(false);
			response.setFailureMessage(ErrorMapping.ERROR.getDescription());
		}
		catch (Exception e) {
			logger.error("Exception", e);
			response.setSucess(false);
			response.setFailureMessage(ErrorMapping.ERROR.getDescription());
		}
		response.setItems(list);
		return response;
	}
	
}
