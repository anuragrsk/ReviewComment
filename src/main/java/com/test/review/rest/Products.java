package com.test.review.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.review.controller.ProductsController;
import com.test.review.db.repository.ProductCRUDInterface;
import com.test.review.rest.elements.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value="Products")

@Path("/api/v1/product/")
@RestController 
@RequestMapping("/api/v1/product/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Products {
	
	private static final Logger logger=Logger.getLogger(Products.class);
	@Autowired
	private ProductCRUDInterface productRepo;
	@Autowired
	private ProductsController productsController;
	
	@Path("/Products")
	@ApiOperation(value = "Get Products")
	@RequestMapping(value="Products",method=RequestMethod.GET,params = { "page", "size" })
	@ResponseBody
	public BaseResponse getProducts(@RequestParam(value="category_id") long categoryId,Pageable req) {
		BaseResponse response=productsController.getProducts(req,categoryId,productRepo);
		logger.debug(" Exit with "+((response!=null && response.isSucess())?"sucess":response.getFailureMessage()));
		return response;
	}
	@ApiResponses(value = { 
			@ApiResponse(code = 5, message = "WE are not able to find product.This product is not valid or discontinued") 
			})
	@Path("/Product")
	@ApiOperation(value = "Get a single Product with review comments")
	@RequestMapping(value="/Product",method=RequestMethod.GET)
	@ResponseBody
	public BaseResponse getProduct(@RequestParam(value="productId") String data) {
		BaseResponse response=productsController.getProduct(Long.parseLong(data),productRepo);
		logger.debug(" Exit with "+((response!=null && response.isSucess())?"sucess":response.getFailureMessage()));
		return response;
	}

}
