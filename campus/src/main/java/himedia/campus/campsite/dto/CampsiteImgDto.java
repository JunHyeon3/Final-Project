package himedia.campus.campsite.dto;

import org.modelmapper.ModelMapper;

import himedia.campus.campsite.entity.CampsiteImg;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CampsiteImgDto {

	private Long campsiteImgId;
	private String campsiteImgName;
	private String campsiteImgPath;
	
	public static ModelMapper mapper = new ModelMapper();
	
	public static CampsiteImgDto of(CampsiteImg campsiteImg) {
		return mapper.map(campsiteImg, CampsiteImgDto.class);
	}
	
}
