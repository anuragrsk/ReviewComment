package com.test.review.db.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.review.db.entity.ReviewCommentsEntiry;
import com.test.review.model.Result;


public interface ReviewCommentsCRUDInterface extends CrudRepository<ReviewCommentsEntiry, Long>{
	@Query(
			"select new com.test.review.model.Result(count(c.review_score) as number_user,sum(c.review_score) as sum_review ,c.catalog_id) from ReviewCommentsEntiry c where c.review_status='Pending' and c.modified_on between :from and :to group by catalog_id")
	List<Result> getReviewCommentsData(@Param("from")Date from,@Param("to")Date to);
  
	@Modifying
	@Query(
			"update ReviewCommentsEntiry c set c.review_status='Complete' where c.review_status='Pending' and c.modified_on between :from and :to ")
	void markReviewCommentComplte(@Param("from")Date from,@Param("to")Date to);
}
