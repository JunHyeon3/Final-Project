package himedia.campus.entity.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import himedia.campus.dto.MemberDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.campsite.FavoriteCampsite;
import himedia.campus.entity.reservation.Reservation;
import himedia.campus.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Setter 
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberNo;
	
	@Enumerated(value = EnumType.STRING)
	private MemberRole memberRole;
	
	private String memberId;
	
	private String memberPw;
	
	private String memberName;
	
	private String memberPhone;
	
	private Integer memberAge;
	
	@OneToOne(mappedBy = "member")
	private Campsite campsite;
	
	@OneToMany(mappedBy = "member")
	private List<FavoriteCampsite> favoriteCampsite;
	
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations = new ArrayList<>();
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

	public static Member create(MemberDto memberDto, PasswordEncoder encoder) {
		Member member = new Member();
		member.setMemberRole(memberDto.getMemberRole());
		member.setMemberId(memberDto.getMemberId());
		member.setMemberPw(encoder.encode(memberDto.getMemberPw()));
		member.setMemberName(memberDto.getMemberName());
		member.setMemberPhone(memberDto.getMemberPhone());
		member.setMemberAge(memberDto.getMemberAge());
		return member;
	}
	
	public void updateMember(MemberDto memberDto) {
		this.memberName = memberDto.getMemberName();
		this.memberPhone = memberDto.getMemberPhone();
		this.memberAge = memberDto.getMemberAge();
	}
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static MemberDto toDto(Member member) {
		return modelMapper.map(member, MemberDto.class);
	}
}
