package himedia.campus.dto.campsite;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import himedia.campus.entity.campsite.Campsite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampsiteDto {

	private Long campsiteId;
	private String campsiteManager;
	@NotEmpty(message = "캠핑장 이름은 필수 입력 항목입니다.")
	private String campsiteName;
	@NotEmpty(message = "캠핑장 소개는 필수 입력 항목입니다.")
	private String campsiteIntroduction;
	@NotEmpty(message = "캠핑장 주소는 필수 입력 항목입니다.")
	private String campsiteAddress;
	@NotEmpty(message = "캠핑장 번호는 필수 입력 항목입니다.")
	private String campsiteTel;
	private String campsiteMainImgPath;
	
	@NotNull(message="이용 요금은 필수 입력 항목입니다.")
	private Integer campsitePrice;
	@NotNull(message="최소 이용 인원은 필수 입력 항목입니다.")
	private Integer campsiteMin;
	@NotNull(message="최대 이용 인원은 입력 항목입니다.")
	private Integer campsiteMax;
	@NotNull(message="체크인 시간은 필수 입력 항목입니다.")
	private Integer campsiteCheckin;
	@NotNull(message="체크인 시간은 필수 입력 항목입니다.")
	private Integer campsiteCheckout;

	@NotEmpty(message = "주변 환경을 하나이상 선택해 주세요.")
	private Set<@Valid String> environments;
	@NotEmpty(message = "편의 시설을 하나이상 선택해 주세요.")
	private Set<@Valid String> facilities;
	@NotEmpty(message = "테마를 하나이상 선택해 주세요.")
	private Set<@Valid String> themes;
	
	private static ModelMapper modelMapper = new ModelMapper();

	public Campsite toEntity() {
		return modelMapper.map(this, Campsite.class);
	}
	
}
