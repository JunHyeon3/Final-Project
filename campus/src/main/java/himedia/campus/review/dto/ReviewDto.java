package himedia.campus.review.dto;

import org.modelmapper.ModelMapper;

import himedia.campus.review.entity.Review;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewDto {

	private Long reviewId;
	private String reviewCampsite;
	private String reviewTitle;
	private String reviewContent;
	private String reviewWriter;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Review toEntity() {
		return modelMapper.map(this, Review.class);
	}
	
}
