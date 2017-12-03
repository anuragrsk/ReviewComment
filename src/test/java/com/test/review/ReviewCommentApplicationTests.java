package com.test.review;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.review.rest.ProductCatalogRestTest;
import com.test.review.rest.ProductCategoriesRestTest;
import com.test.review.rest.ReviewCommnetRestTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ProductCatalogRestTest.class,
	ProductCategoriesRestTest.class,
	ReviewCommnetRestTest.class,
  
})

@SpringBootTest
public class ReviewCommentApplicationTests {

	

}
