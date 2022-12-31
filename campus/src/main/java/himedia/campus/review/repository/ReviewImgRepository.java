package himedia.campus.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.campus.review.entity.ReviewImg;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {
	
	List<ReviewImg> findByReview_ReviewId(Long reviewId);

}
