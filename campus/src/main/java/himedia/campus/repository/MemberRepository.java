package himedia.campus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import himedia.campus.entity.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByMemberId(String memberId);
	Optional<Member> findByMemberNo(Long memberNo);
	Optional<Member> findByMemberName(String memberName);
}
