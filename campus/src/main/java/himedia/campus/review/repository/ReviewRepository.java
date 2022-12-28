package himedia.campus.review.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.campus.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByReviewId(Long reviewId);
}
