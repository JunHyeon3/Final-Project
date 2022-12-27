package himedia.campus.review.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.campsite.entity.Campsite;
import himedia.campus.member.entity.Member;
import himedia.campus.member.service.MemberService;
import himedia.campus.review.dto.ReviewDto;
import himedia.campus.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
	
	private final ReviewService reviewService;
	private final MemberService memberService;
	
	@GetMapping("/reviews")
	public String reviewList(Model model) {
		model.addAttribute("reviews", reviewService.findAllReviews());
		return "review/reviews";
	}
	
	@GetMapping("/reviews/{reviewId}")
	public String reviewDetail() {
		
		log.info("리뷰 상세");
		return "review/review";
	}
	
	@GetMapping("/reviews/post")
	public String reviewAddForm(Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/reviews";
		}
		Member member = memberService.findByMemberId(principal.getName()).get();
		
		ReviewDto reviewDto = new ReviewDto();
		log.info("dlfmaf: " + member.getMemberName());
		reviewDto.setReviewWriter(member.getMemberName());
		model.addAttribute("reviewDto", reviewDto);
		
		List<Campsite> campsites = member.getReservations().stream().map(t -> t.getCampsite()).toList();
		model.addAttribute("campsites", campsites);
		
		return "review/review-add";
	}
	
	@PostMapping("/reviews/post")
	public String reviewAdd(ReviewDto reviewDto, List<MultipartFile> reviewImg) throws Exception {
		reviewService.saveReview(reviewDto, reviewImg);
		return "redirect:/reviews";
	}
	
//	@GetMapping("/reviews/put/{reviewId}")
//	public String reviewEditForm() {
//		
//		log.info("리뷰 수정 페이지");
//		return "review/review-modify";
//	}
	
//	@PutMapping("/reviews/put/{reviewId}")
//	public String reviewEdit() {
//		
//		log.info("리뷰 수정");
//		return "redirect:/reviews/{reivewId}";
//	}
//	
//	@DeleteMapping("/reviews/delete/{reviewId}")
//	public String reviewDelete() {
//		
//		log.info("리뷰 삭제");
//		return "redirect:/reviews";
//	}
	
}
