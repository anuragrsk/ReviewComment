package com.test.review.rest.elements.request;

import com.test.review.model.ReviewComment;

public final class ReviewCommentRequest extends BaseRequest{
	
	private ReviewComment reviewComment = new ReviewComment();

	public ReviewComment getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(ReviewComment reviewComment) {
		this.reviewComment = reviewComment;
	}

	

	

}
