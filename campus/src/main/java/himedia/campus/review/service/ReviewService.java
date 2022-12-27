package himedia.campus.review.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.member.entity.Member;
import himedia.campus.member.service.MemberService;
import himedia.campus.review.dto.ReviewDto;
import himedia.campus.review.entity.Review;
import himedia.campus.review.entity.ReviewImg;
import himedia.campus.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final ReviewImgService reviewImgService;
	private final MemberService memberService;

	public Long saveReview(ReviewDto reviewDto, List<MultipartFile> reviewImgFiles) throws Exception {
		Review review = reviewDto.toEntity();
		Member member = memberService.findByMemberName(reviewDto.getReviewWriter()).get();
		review.setMember(member);
		reviewRepository.save(review);
		
		for(int i=0; i<reviewImgFiles.size(); i++) {
			ReviewImg reviewImg = new ReviewImg();
			reviewImg.setReview(review);
			reviewImgService.saveReviewImg(reviewImg, reviewImgFiles.get(i));
		}
		
		return review.getReviewId();
	}

	public Long updateReview(ReviewDto reviewDto, List<MultipartFile> reviewImgFiles) throws Exception {
		Review findReview = reviewRepository.findByReviewId(reviewDto.getReviewId()).get();
		findReview.updateReview(reviewDto);
		
		List<ReviewImg> findReviewImg = reviewImgService.findByReviewId(reviewDto.getReviewId());
		for (int i = 0; i < reviewImgFiles.size(); i++) {
			reviewImgService.updateReviewImg(findReviewImg.get(i), reviewImgFiles.get(i));
		}
		
		return findReview.getReviewId();
	}

	public void deleteReview(Review review, List<ReviewImg> reviewImgs) throws Exception {
		for (ReviewImg ri : reviewImgs) {
			reviewImgService.deleteCampsiteImg(ri);
		}
		reviewRepository.delete(review);
	}

	public List<Review> findAllReviews() {
		return reviewRepository.findAll();
	}
	
}
