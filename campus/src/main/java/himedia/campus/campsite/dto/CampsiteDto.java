package himedia.campus.campsite.dto;

import org.modelmapper.ModelMapper;

import himedia.campus.campsite.entity.Campsite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampsiteDto {

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
	
	private static ModelMapper modelMapper = new ModelMapper();

	public Campsite toEntity() {
		return modelMapper.map(this, Campsite.class);
	}
	
}
