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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.review.constant.AppTestConstants;
import com.test.review.rest.elements.response.BaseResponse;
import com.test.review.util.ErrorMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ProductCatalogRestTest.class)
@WebAppConfiguration
public class ProductCatalogRestTest {
	
	private static final Logger logger=Logger.getLogger(ProductCatalogRestTest.class);
	TestRestTemplate testTempate= new TestRestTemplate();
	HttpHeaders headers=new HttpHeaders();
	public static final String service ="/product/Products";
	/**
	 * Get products in a category 
	 */
	
	@Test
	public void testProductsRestService() {
		headers.add("Content-Type", "application/xml");
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest+service+"?page=1&size=5&category_id=1", HttpMethod.GET,entity,String.class); 
		logger.debug("Response::"+response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	/**
	 * Test for product
	 */
	@Test
	public void testProductRestService() {
		String method="testProductRestService";
		String service ="/product/Product";
		headers.add("Content-Type", "application/xml");
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest+service+"?productId=100", HttpMethod.GET,entity,String.class); 
		logger.debug("Response::"+response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	/**
	 * invalid product id
	 */
	@Test
	public void testProductRestServiceInValidProductId() {
		String method="testProductRestServiceInValidProductId";
		String service ="/product/Product";
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = testTempate.exchange(AppTestConstants.endPointTest+service+"?productId=1", HttpMethod.GET,entity,String.class); 
		logger.debug("Response::"+response.getBody());
		BaseResponse product;
		try {
			product = mapper.readValue(response.getBody(),BaseResponse.class);
			assertEquals(ErrorMapping.NO_PRODUCT_FOUND.getErrorCode(),product.getErrorCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
