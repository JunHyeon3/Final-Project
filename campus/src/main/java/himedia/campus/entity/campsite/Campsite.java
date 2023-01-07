package himedia.campus.entity.campsite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.modelmapper.ModelMapper;

import himedia.campus.dto.campsite.CampsiteDto;
import himedia.campus.entity.member.Member;
import himedia.campus.entity.reservation.Reservation;
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
	
	@ElementCollection(targetClass=String.class)
	@CollectionTable(name = "themes", joinColumns = @JoinColumn(name = "campsite_id"))
	@Column(name = "themes")
	private Set<String> themes = new HashSet<>();
	
	@ElementCollection(targetClass=String.class)
	@CollectionTable(name = "environments", joinColumns = @JoinColumn(name = "campsite_id"))
	@Column(name = "environments")
	private Set<String> environments = new HashSet<>();
	
	@ElementCollection(targetClass=String.class)
	@CollectionTable(name = "facilities", joinColumns = @JoinColumn(name = "campsite_id"))
	@Column(name = "facilities")
	private Set<String> facilities = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;
	
    @OneToMany(mappedBy = "campsite")
    private List<CampsiteImg> campsiteImgs = new ArrayList<>();
    
    @OneToMany(mappedBy = "campsite")
    private List<Reservation> reservations = new ArrayList<>();
    
    @OneToMany(mappedBy = "campsite")
    private List<FavoriteCampsite> favoriteCampsites = new ArrayList<>();
	
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
		this.themes =  campsiteDto.getThemes();
		this.environments =  campsiteDto.getEnvironments();
		this.facilities =  campsiteDto.getFacilities();
	}
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static CampsiteDto toDto(Campsite campsite) {
		return modelMapper.map(campsite, CampsiteDto.class);
	}
	
}
