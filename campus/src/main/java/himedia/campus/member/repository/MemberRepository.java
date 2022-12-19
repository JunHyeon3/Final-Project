package himedia.campus.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import himedia.campus.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByMemberId(String memberId);
	Optional<Member> findByMemberNo(Long memberNo);
}
