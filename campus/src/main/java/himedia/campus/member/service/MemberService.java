package himedia.campus.member.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import himedia.campus.member.entity.Member;
import himedia.campus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	
	public void duplicateMember(Member member) {
		Member findMember = memberRepository.findByMemberId(member.getMemberId());
		if(findMember != null) {
			throw new IllegalStateException("이미 등록된 회원입니다.");
		}
	}

	public Long saveMember(Member member) {
		duplicateMember(member);
		return memberRepository.save(member).getMemberNo();
	}
	
	
}
