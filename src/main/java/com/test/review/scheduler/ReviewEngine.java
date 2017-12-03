package com.test.review.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.review.constants.AppConstants;
import com.test.review.db.entity.ProductEntity;
import com.test.review.db.repository.ProductCRUDInterface;
import com.test.review.db.repository.ReviewCommentsCRUDInterface;
import com.test.review.model.Result;

@Component
public class ReviewEngine {

	private static final Logger logger=Logger.getLogger(ReviewEngine.class);
	@Autowired
	private ReviewCommentsCRUDInterface reviewCommentsCRUD;
	@Autowired
	ProductCRUDInterface productRepo;

	@Transactional
	@Scheduled(fixedRate = 1 * 60 * 1000)
	/**
	 * Review engine will fetch all the pending review at fix interval 
	 * 1. Collect all pending review 
	 * 2. Get Existing Product data from product catalog table 
	 * 3.Update product table for aggregated data 
	 * 		-- old+new user count 
	 * 		-- old review +new review score
	 *  4. update new score at product table 
	 *  5. Mark all the reviews as complete.
	 */
	public void updateProductRating() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, AppConstants.LOOK_BACK_DAYS);
		Date toDate = new Date();
		// Fetch pending review data
		List<Result> q = reviewCommentsCRUD.getReviewCommentsData(c.getTime(), toDate);
		ProductEntity product = null;
		double sum = 0;
		long users = 0;
		// update data to product catalog table
		for (Result row : q) {
			product = productRepo.findActiveById(row.getCatalog_id());
			sum = product.getSum_review() + row.getSum_review();
			users = product.getNumber_user() + row.getNumber_user();
			product.setNumber_user(users);
			product.setSum_review(sum);
			product.setReview_score(sum / users);
			productRepo.save(product);
			sum = 0;
			users = 0;
			logger.debug (product.toString());
		}
		// mark reviews as complete
		reviewCommentsCRUD.markReviewCommentComplte(c.getTime(), toDate);
		logger.debug( "Exit.");
	}
}
