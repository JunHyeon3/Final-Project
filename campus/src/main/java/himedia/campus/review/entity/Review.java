package himedia.campus.review.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.modelmapper.ModelMapper;

import himedia.campus.member.entity.Member;
import himedia.campus.review.dto.ReviewDto;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	private String reviewCampsite;
	private String reviewTitle;
	private String reviewContent;
	private String reviewWriter;
	private Integer reviewViews;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;
	
	public void updateReview(ReviewDto reviewDto) {
		this.reviewTitle =  reviewDto.getReviewTitle();
		this.reviewContent =  reviewDto.getReviewContent();
	}
	
	private static ModelMapper modelMapper = new ModelMapper();

	public static ReviewDto toDto(Review review) {
		return modelMapper.map(review, ReviewDto.class);
	}
	
}
