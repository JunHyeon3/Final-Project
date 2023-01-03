package himedia.campus.dto.review;

import org.modelmapper.ModelMapper;

import himedia.campus.entity.review.Review;
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
