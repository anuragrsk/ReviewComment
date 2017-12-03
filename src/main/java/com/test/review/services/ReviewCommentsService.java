package com.test.review.services;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.test.review.db.entity.ReviewCommentsEntiry;
import com.test.review.db.repository.ReviewCommentsCRUDInterface;
import com.test.review.model.ReviewComment;
/**
 * This service class will interact with repository to store comments
 * @author anuraag
 *
 */
@Service
public class ReviewCommentsService {
	private static final Logger logger=Logger.getLogger(ReviewCommentsService.class);
	
	
	public boolean addComments(ReviewComment request,ReviewCommentsCRUDInterface reviewCommentsRepo) {
		
		boolean isSucess = false;
		ReviewCommentsEntiry entityObj = new ReviewCommentsEntiry();
		populateCommnets(request,entityObj);
		entityObj=reviewCommentsRepo.save(entityObj);
		if(entityObj!=null && entityObj.getReview_comment_id()>0)
			isSucess=true;
		logger.debug("Exit "+isSucess);
		return isSucess;
	}
	
/**
 * Copy from request to entity class
 * @param request
 * @param entityObj 
 * @return
 */
	private void populateCommnets(ReviewComment request, ReviewCommentsEntiry entityObj) {
		BeanUtils.copyProperties(request, entityObj);
	}

}
