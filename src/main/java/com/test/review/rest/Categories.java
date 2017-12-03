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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.review.controller.CategoriesController;
import com.test.review.db.repository.CategoryCRUDInterface;
import com.test.review.rest.elements.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Categories")

@Path("/api/v1/product/")
@RestController 
@RequestMapping("/api/v1/product/")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML })
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML })
public class Categories {
	
private static final Logger logger=Logger.getLogger(Categories.class);
@Autowired
private CategoryCRUDInterface categoryRepo;
@Autowired
private CategoriesController categoriesController;
/**
 * 
 * @param req
 * @return
 */
	@Path("/Categories")
	@ApiOperation(value = "Get Categories")
	
	@RequestMapping(value="Categories",method=RequestMethod.GET,params = { "page", "size" })
	@ResponseBody
	public BaseResponse getCategories(Pageable req) {
		BaseResponse response=categoriesController.getCategories(req,categoryRepo);
		logger.debug(" Response with "+((response!=null && response.isSucess())?"sucess":response.getFailureMessage()));
		return response;
	}
}
