package himedia.campus.repository.review;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import himedia.campus.entity.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByReviewId(Long reviewId);

	Page<Review> findByReviewCampsiteContaining(String searchCampsite, Pageable pageable);
}
