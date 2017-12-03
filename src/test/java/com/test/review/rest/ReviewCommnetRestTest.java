package com.test.review.rest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.review.constant.AppTestConstants;
import com.test.review.model.ReviewComment;
import com.test.review.rest.elements.request.ReviewCommentRequest;
import com.test.review.rest.elements.response.BaseResponse;
import com.test.review.util.ErrorMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductCatalogRestTest.class)
@WebAppConfiguration
public class ReviewCommnetRestTest {

	private static final Logger logger=Logger.getLogger(ReviewCommnetRestTest.class);
	TestRestTemplate testTempate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	public static final String service = "/user/Comments";

	/**
	 * This test case will test for failure when no values are passed to REST
	 * services
	 * 
	 * 
	 */
	@Test
	public void testCommentsRestService() {
		String method = "testProductsRestService";
		ReviewCommentRequest req = new ReviewCommentRequest();
		HttpEntity<ReviewCommentRequest> entity = new HttpEntity<ReviewCommentRequest>(req, headers);
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest + service, HttpMethod.POST,
				entity, String.class);
		logger.debug("Response::" + response.getBody());
		try {
			BaseResponse product = mapper.readValue(response.getBody(), BaseResponse.class);
			assertEquals(false, product.isSucess());

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Test case for invalid email
	 */

	@Test
	public void testCommentsRestServiceEmailinValid() {
		String method = "testProductsRestService";
		ReviewCommentRequest req = new ReviewCommentRequest();
		ReviewComment reviewComment = new ReviewComment();
		reviewComment.setReviewer_email("am.com");
		req.setReviewComment(reviewComment);
		HttpEntity<ReviewCommentRequest> entity = new HttpEntity<ReviewCommentRequest>(req, headers);
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest + service, HttpMethod.POST,
				entity, String.class);
		logger.debug("Response::" + response.getBody());

		try {
			BaseResponse product = mapper.readValue(response.getBody(), BaseResponse.class);
			assertEquals(false, product.isSucess());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test case when no comments are entered
	 */
	@Test
	public void testCommentsRestServiceCommentsinValidNoComments() {
		String method = "testCommentsRestServiceCommentsinValidNoComments";
		ReviewCommentRequest req = new ReviewCommentRequest();
		ReviewComment reviewComment = new ReviewComment();
		reviewComment.setReviewer_email("am@yahoo.com");
		reviewComment.setReview_score(2.0);
		reviewComment.setReview_comments(null);
		req.setReviewComment(reviewComment);
		HttpEntity<ReviewCommentRequest> entity = new HttpEntity<ReviewCommentRequest>(req, headers);
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest + service, HttpMethod.POST,
				entity, String.class);
		logger.debug( "Response::" + response.getBody());

		try {
			BaseResponse product = mapper.readValue(response.getBody(), BaseResponse.class);
			assertEquals(ErrorMapping.NO_COMMENTS_ENTERED.getErrorCode(), product.getErrorCode());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test case when comments are too short
	 */

	@Test
	public void testCommentsRestServiceCommentsinValid() {
		String method = "testCommentsRestServiceCommentsinValid";
		ReviewCommentRequest req = new ReviewCommentRequest();
		ReviewComment reviewComment = new ReviewComment();
		reviewComment.setReviewer_email("am@yahoo.com");
		reviewComment.setReview_score(2.0);
		reviewComment.setReview_comments("abc");
		req.setReviewComment(reviewComment);
		HttpEntity<ReviewCommentRequest> entity = new HttpEntity<ReviewCommentRequest>(req, headers);
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest + service, HttpMethod.POST,
				entity, String.class);
		logger.debug( "Response::" + response.getBody());

		try {
			BaseResponse product = mapper.readValue(response.getBody(), BaseResponse.class);
			assertEquals(ErrorMapping.INVALID_COMMENTS.getErrorCode(), product.getErrorCode());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test Case when rating is not valid
	 */
	@Test
	public void testCommentsRestServiceCommentsinValidRating() {
		String method = "testCommentsRestServiceCommentsinValidRating";
		ReviewCommentRequest req = new ReviewCommentRequest();
		ReviewComment reviewComment = new ReviewComment();
		reviewComment.setReviewer_email("am@am.com");
		reviewComment.setReview_comments("abcdef");
		req.setReviewComment(reviewComment);
		HttpEntity<ReviewCommentRequest> entity = new HttpEntity<ReviewCommentRequest>(req, headers);
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest + service, HttpMethod.POST,
				entity, String.class);
		logger.debug( "Response::" + response.getBody());
		try {
			BaseResponse product = mapper.readValue(response.getBody(), BaseResponse.class);
			assertEquals(ErrorMapping.INVALID_RATING.getErrorCode(), product.getErrorCode());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sucess Case
	 */
	@Test
	public void testCommentsRestServiceComments() {
		String method = "testCommentsRestServiceCommentsinValidRating";
		ReviewCommentRequest req = new ReviewCommentRequest();
		ReviewComment reviewComment = new ReviewComment();
		reviewComment.setReviewer_email("am@am.com");
		reviewComment.setReview_comments("abcdef");
		reviewComment.setReviewer_name("Test");
		reviewComment.setCatalog_id(100);
		reviewComment.setReview_score(5);
		req.setReviewComment(reviewComment);
		HttpEntity<ReviewCommentRequest> entity = new HttpEntity<ReviewCommentRequest>(req, headers);
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest + service, HttpMethod.POST,
				entity, String.class);
		ObjectMapper mapper = new ObjectMapper();

		logger.debug( "Response::" + response.getBody());
		
		try {
			logger.debug( "Request::" + mapper.writeValueAsString(req));
			BaseResponse product = mapper.readValue(response.getBody(), BaseResponse.class);
			assertEquals(product.isSucess(), true);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
