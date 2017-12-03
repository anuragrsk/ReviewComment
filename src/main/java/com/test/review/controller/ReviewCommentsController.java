package com.test.review.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import com.test.review.constants.AppConstants;
import com.test.review.db.repository.ReviewCommentsCRUDInterface;
import com.test.review.model.ReviewComment;
import com.test.review.rest.elements.response.BaseResponse;
import com.test.review.services.ReviewCommentsService;
import com.test.review.util.ErrorMapping;
import com.test.review.util.ReviewStatus;
import com.test.review.util.UtilHelper;
/**
 * This class will add comments to database
 * @author anuraag
 *
 */
@Controller
public class ReviewCommentsController {
	private static final Logger logger=Logger.getLogger(ReviewCommentsController.class);
	@Autowired
	private ReviewCommentsService reviewCommentsService;
	
	/**
	 * 
	 * @param request
	 * @param reviewCommentsCRUD
	 * @return
	 */
	
	public BaseResponse addComments(ReviewComment request,ReviewCommentsCRUDInterface reviewCommentsRepo) {
		BaseResponse response = new BaseResponse();
		request.setReview_status(ReviewStatus.PENDING.getStatus());
		boolean isValid = validateRequest(request, response);
		if (isValid) {
			if (!UtilHelper.validateCommnets(request.getReview_comments()))
				request.setReview_status(ReviewStatus.REVIEW.getStatus());
			try {
				boolean flag = reviewCommentsService.addComments(request,reviewCommentsRepo);
				response.setSucess(flag);
				} 
			catch (DataAccessException ee) {
				logger.error("DataBase Exception", ee);
				response.setSucess(false);
				response.setFailureMessage(ErrorMapping.ERROR.getDescription());
				}
			catch (Exception e) {
				logger.error("Exception", e);
				response.setSucess(false);
				response.setFailureMessage(ErrorMapping.ERROR.getDescription());
				}

		} else {
			response.setSucess(false);
		}

		return response;
	}
/**
 * Validate request for following
 * - comments can not be blank or less then 5 char
 * - email can not be blank or invlaid
 *  - review rating cannot be 0.
 * @param request
 * @param response
 * @return
 */
	private boolean validateRequest(ReviewComment request, BaseResponse response) {
		boolean isValid=true;
		if(request!=null) {
			if(!UtilHelper.isNotNullOrBlank(request.getReviewer_email()) 
					||( !UtilHelper.isNotNullOrBlank(request.getReviewer_email()) 
					&& !UtilHelper.isValidEmail(request.getReviewer_email()))){
				response.setFailureMessage(ErrorMapping.INVALID_EMAIL.getDescription());
				response.setErrorCode(ErrorMapping.INVALID_EMAIL.getErrorCode());
				isValid=false;
			}
			if(isValid && request.getReview_score()<=0) {
				isValid=false;
				response.setFailureMessage(ErrorMapping.INVALID_RATING.getDescription());
				response.setErrorCode(ErrorMapping.INVALID_RATING.getErrorCode());
			}
			if(isValid && 
					!UtilHelper.isNotNullOrBlank(request.getReview_comments()) ) {
				isValid=false;
				response.setFailureMessage(ErrorMapping.NO_COMMENTS_ENTERED.getDescription());
				response.setErrorCode(ErrorMapping.NO_COMMENTS_ENTERED.getErrorCode());
			}
			if(isValid && 
					UtilHelper.isNotNullOrBlank(request.getReview_comments()) 
					&& request.getReview_comments().length()<=AppConstants.COMMENTS_LENGTH) {
				isValid=false;
				response.setFailureMessage(ErrorMapping.INVALID_COMMENTS.getDescription());
				response.setErrorCode(ErrorMapping.INVALID_COMMENTS.getErrorCode());

			}
			
		}
		return isValid;
	}

}
