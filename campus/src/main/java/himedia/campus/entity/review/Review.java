package himedia.campus.entity.review;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.modelmapper.ModelMapper;

import himedia.campus.dto.review.ReviewDto;
import himedia.campus.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DynamicInsert
public class Review {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	private String reviewCampsite;
	private String reviewTitle;
	private String reviewContent;
	private String reviewWriter;
	@ColumnDefault("0")
	private Integer reviewViews;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;
	
    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewImg> reviewImgs = new ArrayList<>();
	
	public void updateReviewViews(Integer views) {
		this.reviewViews = views;
	}
	
	public void updateReview(ReviewDto reviewDto) {
		this.reviewTitle =  reviewDto.getReviewTitle();
		this.reviewContent =  reviewDto.getReviewContent();
	}
	
	private static ModelMapper modelMapper = new ModelMapper();

	public static ReviewDto toDto(Review review) {
		return modelMapper.map(review, ReviewDto.class);
	}
	
}
