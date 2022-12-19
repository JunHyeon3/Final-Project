package himedia.campus.member.service;

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

import himedia.campus.member.entity.Member;
import himedia.campus.member.entity.MemberRole;
import himedia.campus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	
	@Transactional
	public Long saveMember(Member member) {
		checkDuplication(member);
		return memberRepository.save(member).getMemberNo();
	}
	
	public Optional<Member> findMember(Long memberNo) {
		memberRepository.findByMemberNo(memberNo);
		return null;
	}
	
	private void checkDuplication(Member member) {
		Member findMember = memberRepository.findByMemberId(member.getMemberId());
		if(findMember != null){
			throw new IllegalStateException("이미 등록된 회원입니다.");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByMemberId(memberId);

        if(findMember == null){
            throw new UsernameNotFoundException("등록되지 않은 회원입니다.");
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(findMember.getMemberRole())) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } 
        else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }
        
        return new User(findMember.getMemberId(), findMember.getMemberPw(), authorities);
	}
	
}
