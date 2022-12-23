package himedia.campus.campsite.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Campsite {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long campsiteId;
	private String campsiteManager;
	private String campsiteName;
	private String campsiteIntroduction;
	private String campsiteAddress;
	private String campsiteTel;
	private String campsiteMainImgPath;
	
	private Integer campsitePrice;
	private Integer campsiteMin;
	private Integer campsiteMax;
	private Integer campsiteCheckin;
	private Integer campsiteCheckout;
	
	private String campsiteEnvironment;
	private String campsiteFacilitie;
	private String campsiteTheme;
	
	public void updateCampsiteMainImg(String campsiteMainImgPath) {
		this.campsiteMainImgPath = campsiteMainImgPath;
	}
	
}
