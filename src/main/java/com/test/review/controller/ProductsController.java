package com.test.review.controller;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.test.review.db.repository.ProductCRUDInterface;
import com.test.review.exception.ReviewCommentsException;
import com.test.review.model.Product;
import com.test.review.rest.elements.response.BaseResponse;
import com.test.review.rest.elements.response.ProductResponse;
import com.test.review.rest.elements.response.ProductsResponse;
import com.test.review.services.ProductService;
import com.test.review.util.ErrorMapping;
/**
 * Products controller class
 * @author anuraag
 *
 */
@Controller
public class ProductsController {
	
	private static final Logger logger=Logger.getLogger(ProductsController.class);
	@Autowired
	private ProductService productService;
	/**
	 * 
	 * @param productCrud
	 * @param req 
	 * @param categoryId 
	 * @return
	 */
	public BaseResponse getProducts( Pageable req, long categoryId,ProductCRUDInterface productRepo){
		ProductsResponse response = new ProductsResponse();
		response.setSucess(true);
		List<Product> list=Collections.emptyList();
		try {
			list = productService.getProducts(req,response,categoryId,productRepo);	
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
	/**
	 * 
	 * @param curd
	 * @param id
	 * @return
	 */
	public BaseResponse getProduct( long id,ProductCRUDInterface productRepo) {
		ProductResponse response = new ProductResponse();
		response.setSucess(true);
		Product product=null;
		try {
			product = productService.getProduct(id,productRepo);	
		}catch (DataAccessException ee) {
			logger.error("DataBase Exception", ee);
			response.setSucess(false);
			response.setFailureMessage(ErrorMapping.ERROR.getDescription());
		}catch(ReviewCommentsException ee) {
			logger.error("ReviewCommentsException", ee);
			response.setSucess(false);
			response.setErrorCode(ee.getErrorCode());
			response.setFailureMessage(ee.getMessage());
		}
		catch (Exception e) {
			logger.error("ReviewCommentsException", e);
			response.setSucess(false);
			response.setFailureMessage(ErrorMapping.ERROR.getDescription());
		}

		response.setItem(product);
		return response;
	}
}
