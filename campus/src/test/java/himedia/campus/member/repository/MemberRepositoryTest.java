package himedia.campus.member.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import himedia.campus.member.entity.Member;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;
	
	@Test
	void save() {
		Member member = new Member();
		member.setMemberId("user1");
		member.setMemberPw("asdfasdf");
		member.setMemberName("홍길동");
		member.setMemberPhone("010-1111-2222");
		member.setMemberAge(26);
		member.setMemberRole("user");
		
		Member savedMember = memberRepository.save(member);
		
		assertThat(savedMember.getMemberName()).isEqualTo("홍길동");
	}

}
