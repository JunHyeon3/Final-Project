package himedia.campus.review.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.campsite.entity.Campsite;
import himedia.campus.member.entity.Member;
import himedia.campus.member.service.MemberService;
import himedia.campus.review.dto.ReviewDto;
import himedia.campus.review.entity.Review;
import himedia.campus.review.entity.ReviewImg;
import himedia.campus.review.service.ReviewImgService;
import himedia.campus.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
	
	private final ReviewService reviewService;
	private final ReviewImgService reviewImgService;
	private final MemberService memberService;
	
	@GetMapping("/reviews")
	public String reviewList(@PageableDefault(sort="reviewId", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
		Page<Review> pageList = reviewService.pageList(pageable);

		int nowPage = pageList.getPageable().getPageNumber() + 1;
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, pageList.getTotalPages());


        model.addAttribute("reviews", pageList);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

		
		return "review/reviews";
	}
	
	@GetMapping("/reviews/{reviewId}")
	public String reviewDetail(@PathVariable Long reviewId, Principal principal, Model model) {
		Review review = reviewService.findByReviewId(reviewId).get();
		model.addAttribute("review", review);
		
		List<String> reviewImgPaths = reviewImgService.findByReviewId(reviewId).stream().map(t-> t.getReviewImgPath()).toList();
		model.addAttribute("reviewImgPaths", reviewImgPaths);
		
		if(principal != null) {
			boolean isWriter = memberService.findByMemberId(principal.getName()).get().getReviews().contains(review);
			model.addAttribute("isWriter", isWriter);
		}
		return "review/review";
	}
	
	@GetMapping("/reviews/post")
	public String reviewAddForm(Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/reviews";
		}
		Member member = memberService.findByMemberId(principal.getName()).get();
		
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setReviewWriter(member.getMemberName());
		model.addAttribute("reviewDto", reviewDto);
		
		List<Campsite> campsites = member.getReservations().stream().map(t -> t.getCampsite()).toList();
		model.addAttribute("campsites", campsites);
		
		return "review/review-write";
	}
	
	@PostMapping("/reviews/post")
	public String reviewAdd(ReviewDto reviewDto, List<MultipartFile> reviewImg) throws Exception {
		reviewService.saveReview(reviewDto, reviewImg);
		return "redirect:/reviews";
	}
	
	@GetMapping("/reviews/put/{reviewId}")
	public String reviewEditForm(@PathVariable Long reviewId, Model model) {
		Review findReview = reviewService.findByReviewId(reviewId).get();
		
		model.addAttribute("reviewDto", Review.toDto(findReview));
		log.info("reviewId : " + findReview.getReviewId());
		log.info("reviewId : " + Review.toDto(findReview).getReviewId());
		return "review/review-modify";
	}
	
	@PutMapping("/reviews/put/{reviewId}")
	public String reviewEdit(ReviewDto reviewDto, @RequestParam List<MultipartFile> reviewImgs) throws Exception {
		reviewService.updateReview(reviewDto, reviewImgs);
		return "redirect:/reviews";
	}
	
	@DeleteMapping("/reviews/delete/{reviewId}")
	public String reviewDelete(@PathVariable Long reviewId) throws Exception {
		Review review = reviewService.findByReviewId(reviewId).get();
		List<ReviewImg> reviewImg = reviewImgService.findByReviewId(reviewId);
		reviewService.deleteReview(review, reviewImg);
		return "redirect:/reviews";
	}
	
}