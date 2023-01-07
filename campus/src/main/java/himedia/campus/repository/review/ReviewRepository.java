package himedia.campus.repository.review;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.campus.entity.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByReviewId(Long reviewId);
	
	@Query(value = "SELECT * FROM REVIEW", nativeQuery = true)	
	Page<Review> findAll(Pageable pageable);
	
	@Query(value = "SELECT * FROM REVIEW R WHERE R.REVIEW_CAMPSITE LIKE %?%", nativeQuery = true)	
	Page<Review> findBySearchCampsite(String searchCampsite, Pageable pageable);
}
