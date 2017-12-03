package com.test.review.rest;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.test.review.constant.AppTestConstants;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ProductCategoriesRestTest.class)
@WebAppConfiguration
public class ProductCategoriesRestTest {

	private static final Logger logger=Logger.getLogger(ProductCategoriesRestTest.class);
	TestRestTemplate testTempate= new TestRestTemplate();
	HttpHeaders headers=new HttpHeaders();
	public static final String service ="product/Categories";
	/**
	 * Get product Categories
	 */
	
	@Test
	public void testCategoriesRestService() {
		String method="testCategoriesRestService";
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest+service+"?page=0&size=5", HttpMethod.GET,entity,String.class); 
		logger.debug("Response::"+response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
}
