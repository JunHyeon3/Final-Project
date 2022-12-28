package himedia.campus.member.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.password.PasswordEncoder;

import himedia.campus.member.dto.MemberDto;
import himedia.campus.reservation.entity.Reservation;
import himedia.campus.review.entity.Review;
import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
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
	
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();
    
    @OneToMany(mappedBy = "member")
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
	
}
