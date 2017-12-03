package com.test.review.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.review.controller.ReviewCommentsController;
import com.test.review.db.repository.ReviewCommentsCRUDInterface;
import com.test.review.rest.elements.request.ReviewCommentRequest;
import com.test.review.rest.elements.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="ReviewComments")
@RestController
@RequestMapping("/api/v1/user/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewComments {
	
	@Autowired
	private ReviewCommentsCRUDInterface reviewCommentsRepo;
	@Autowired
	private ReviewCommentsController reviewCommentsController;
	@ApiOperation(value = "Submit comments")
	@ApiResponses(value = { 
			@ApiResponse(code = 1, message = "Please enter a valid email."), 
			@ApiResponse(code = 2, message = "Please enter comments."),
			@ApiResponse(code = 3, message = "Please provide valid rating.Please provide value between 0 and 5."),
			@ApiResponse(code = 4, message = "Please enter comments. No comments were entered."),
			@ApiResponse(code = 90000, message = "We are unable to process your request. Please try after some time.")
			})
	@RequestMapping(value = "Comments", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse comments(@RequestBody ReviewCommentRequest request) {
		BaseResponse response = null;
		response=reviewCommentsController.addComments(request.getReviewComment(),reviewCommentsRepo);
		return response;
	}

}
