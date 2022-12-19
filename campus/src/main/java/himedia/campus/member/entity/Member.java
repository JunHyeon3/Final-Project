package himedia.campus.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.password.PasswordEncoder;

import himedia.campus.member.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberNo;
	
	private String memberRole;
	
	private String memberId;
	
	private String memberPw;
	
	private String memberName;
	
	private String memberPhone;
	
	private Integer memberAge;

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
