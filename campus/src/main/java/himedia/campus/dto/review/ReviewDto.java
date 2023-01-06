package himedia.campus.dto.review;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import himedia.campus.entity.review.Review;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewDto {

	private Long reviewId;
	@NotBlank(message = "이용한 캠핑장을 선택해 주세요.")
	private String reviewCampsite;
	@NotBlank(message = "제목을 입력해 주세요.")
	private String reviewTitle;
	@NotBlank(message = "내용을 입력해 주세요.")
	private String reviewContent;
	private String reviewWriter;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Review toEntity() {
		return modelMapper.map(this, Review.class);
	}
	
}
