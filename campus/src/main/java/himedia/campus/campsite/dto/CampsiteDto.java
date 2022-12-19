package himedia.campus.campsite.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.modelmapper.ModelMapper;

import himedia.campus.campsite.entity.Campsite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampsiteDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long campsiteId;
	private String campsiteName;
	private String campsiteIntroduction;
	private String campsiteAddress;
	private String campsiteTel;

	private Integer campsitePrice;
	private Integer campsiteMin;
	private Integer campsiteMax;
	private Integer campsiteCheckin;
	private Integer campsiteCheckout;

	private String campsiteEnvironment;
	private String campsiteFacilitie;
	private String campsiteTheme;
	
	private List<CampsiteImgDto> campsiteImgDtoList = new ArrayList<>();
	private List<Long> campsiteImgId = new ArrayList<>();
	

	private static ModelMapper modelMapper = new ModelMapper();

	public Campsite createCampsite() {
		return modelMapper.map(this, Campsite.class);
	}

	public static CampsiteDto of(Campsite Campsite) {
		return modelMapper.map(Campsite, CampsiteDto.class);
	}

}
