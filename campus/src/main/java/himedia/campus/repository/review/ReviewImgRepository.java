package himedia.campus.repository.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.campus.entity.review.ReviewImg;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {
	
	List<ReviewImg> findByReview_ReviewId(Long reviewId);

}
