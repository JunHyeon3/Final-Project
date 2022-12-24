package himedia.campus.campsite.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter
public class CampsiteImg {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long campsiteImgId;
	private String campsiteImgOriginal;
	private String campsiteImgName;
	private String campsiteImgPath;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campsite_id")
	private Campsite campsite;
	
	public void updateCampsiteImg(String campsiteImgName, String campsiteImgOriginal, String campsiteImgPath) {
		this.campsiteImgOriginal = campsiteImgOriginal;
		this.campsiteImgName = campsiteImgName;
		this.campsiteImgPath = campsiteImgPath;
	}
	 	
}
