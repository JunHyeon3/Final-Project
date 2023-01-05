package himedia.campus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import himedia.campus.dto.MemberDto;
import himedia.campus.entity.member.Member;
import himedia.campus.entity.member.MemberRole;
import himedia.campus.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	private void checkDuplication(Member member) {
		Optional<Member> findMember = memberRepository.findByMemberId(member.getMemberId());
		if(findMember.isPresent()){
			throw new IllegalStateException("이미 등록된 회원입니다.");
		}
	}
	
	public Long saveMember(Member member) {
		checkDuplication(member);
		return memberRepository.save(member).getMemberNo();
	}
	
	public void updateMember(MemberDto memberDto) {
		Member findMember = memberRepository.findByMemberId(memberDto.getMemberId()).get();
		findMember.updateMember(memberDto);
	}
	
	public Optional<Member> findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId);
	}
	
	public Optional<Member> findByMemberName(String memberName) {
		return memberRepository.findByMemberName(memberName);
	}
	
	public Optional<Member> findByMemberNo(Long memberNo) {
		return memberRepository.findByMemberNo(memberNo);
	}
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByMemberId(memberId).get();
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(findMember.getMemberRole().name().equals("ADMIN")) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } 
        else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }
        
        return new User(findMember.getMemberId(), findMember.getMemberPw(), authorities);
	}

	public void deleteMember(Member member) {
		memberRepository.delete(member);
	}
	
}
