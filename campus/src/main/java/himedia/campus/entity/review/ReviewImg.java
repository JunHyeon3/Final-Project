package himedia.campus.entity.review;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImg {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewImgId;
	private String reviewImgName;
	private String reviewImgOriginal;
	private String reviewImgPath;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id")
	private Review review;
	
	public void updateReviewImg(String reviewImgName, String reviewImgOriginal, String reviewImgPath) {
		this.reviewImgName = reviewImgName;
		this.reviewImgOriginal = reviewImgOriginal;
		this.reviewImgPath = reviewImgPath;
	}
	
}
