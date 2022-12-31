package himedia.campus.campsite.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.modelmapper.ModelMapper;

import himedia.campus.campsite.dto.CampsiteDto;
import himedia.campus.member.entity.Member;
import himedia.campus.reservation.entity.Reservation;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;
	
    @OneToMany(mappedBy = "campsite")
    private List<CampsiteImg> campsiteImgs = new ArrayList<>();
    
    @OneToMany(mappedBy = "campsite")
    private List<Reservation> reservations = new ArrayList<>();
	
	public void updateCampsiteMainImg(String campsiteMainImgPath) {
		this.campsiteMainImgPath = campsiteMainImgPath;
	}
	
	public void updateCampsite(CampsiteDto campsiteDto) {
		this.campsiteName =  campsiteDto.getCampsiteName();
		this.campsiteIntroduction =  campsiteDto.getCampsiteIntroduction();
		this.campsiteAddress =  campsiteDto.getCampsiteAddress();
		this.campsiteTel =  campsiteDto.getCampsiteTel();
		this.campsitePrice =  campsiteDto.getCampsitePrice();
		this.campsiteMin =  campsiteDto.getCampsiteMin();
		this.campsiteMax =  campsiteDto.getCampsiteMax();
		this.campsiteCheckin =  campsiteDto.getCampsiteCheckin();
		this.campsiteCheckout =  campsiteDto.getCampsiteCheckout();
		this.campsiteEnvironment =  campsiteDto.getCampsiteEnvironment();
		this.campsiteFacilitie =  campsiteDto.getCampsiteFacilitie();
		this.campsiteTheme =  campsiteDto.getCampsiteTheme();
	}
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static CampsiteDto toDto(Campsite campsite) {
		return modelMapper.map(campsite, CampsiteDto.class);
	}
	
}
