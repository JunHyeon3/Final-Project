package himedia.campus.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.dto.review.ReviewDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.member.Member;
import himedia.campus.entity.review.Review;
import himedia.campus.entity.review.ReviewImg;
import himedia.campus.service.MemberService;
import himedia.campus.service.review.ReviewImgService;
import himedia.campus.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
	
	private final ReviewService reviewService;
	private final ReviewImgService reviewImgService;
	private final MemberService memberService;
	
	@GetMapping(value={"/reviews"})
	public String reviewList(@RequestParam(required = false) String searchCampsite, @PageableDefault(sort="review_Id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
		Page<Review> reviewList = null;
		if(searchCampsite != null) {
			reviewList = reviewService.findBySearchCampsite(searchCampsite, pageable);
		} 
		else {
			reviewList = reviewService.pageList(pageable);
		}
		
		int nowPage = reviewList.getPageable().getPageNumber()+1;                                                                                                                                                                                                                                                                                                                                                                                                               ;
		int startPage = 1;
		int endPage = reviewList.getTotalPages();
		if(reviewList.getTotalPages() >= 10) {
			startPage =  Math.max(nowPage-5, 1);
			endPage = Math.min(nowPage+4, reviewList.getTotalPages());
			if(nowPage < 7) {
				endPage = 10;
			} 
		}
		
        model.addAttribute("reviews", reviewList);
        model.addAttribute("searchCampsite", searchCampsite);
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
		
		reviewService.updateViews(review, review.getReviewViews()+1);
		return "review/review";
	}
	
	@GetMapping("/reviews/post")
	public String reviewAddForm(Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/member/login";
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
	public String reviewAdd(@Validated ReviewDto reviewDto, BindingResult bindingResult, List<MultipartFile> reviewImg) throws Exception {
		if(bindingResult.hasErrors()) {
			return "/review/review-write";
		}
		
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
	public String reviewEdit(ReviewDto reviewDto, @RequestParam List<MultipartFile> reviewImg) throws Exception {
		reviewService.updateReview(reviewDto, reviewImg);
		return "redirect:/reviews/" + reviewDto.getReviewId();
	}
	
	@DeleteMapping("/reviews/delete/{reviewId}")
	public String reviewDelete(@PathVariable Long reviewId) throws Exception {
		Review review = reviewService.findByReviewId(reviewId).get();
		List<ReviewImg> reviewImg = reviewImgService.findByReviewId(reviewId);
		reviewService.deleteReview(review, reviewImg);
		return "redirect:/reviews";
	}
	
}
