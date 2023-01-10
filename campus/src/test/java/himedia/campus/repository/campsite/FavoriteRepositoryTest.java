package himedia.campus.repository.campsite;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import himedia.campus.dto.MemberDto;
import himedia.campus.dto.campsite.CampsiteDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.campsite.FavoriteCampsite;
import himedia.campus.entity.member.Member;
import himedia.campus.entity.member.MemberRole;
import himedia.campus.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
@Slf4j
class FavoriteRepositoryTest {

	@Autowired FavoriteRepository favoriteRepository;
	@Autowired CampsiteRepository campsiteRepository;
	@Autowired MemberRepository memberRepository;
	@Autowired PasswordEncoder encoder;
	
	private Member testMember1;
	private Member testMember2;
	
	private Campsite testCampsite1;
	private Campsite testCampsite2;
	
	@BeforeAll
	public void createTestCampsite() {
		MemberDto memberDto1 = new MemberDto(MemberRole.ADMIN, "user1", "asdfasdf", 
										"이미자", "010-1111-1111", 22);
		MemberDto memberDto2 = new MemberDto(MemberRole.ADMIN, "user2", "asdfasdf", 
										"권상우", "010-1111-1111", 22);
		testMember1 = memberRepository.save(Member.create(memberDto1, encoder));
		testMember2 = memberRepository.save(Member.create(memberDto2, encoder));
		
		CampsiteDto campsiteDto1 = new CampsiteDto(null, "이미자", "테스트 캠핑장1", "테스트 캠핑장 소개1", 
										"테스트 캠핑장 주소1", "테스트 캠핑장 번호1", "대표 이미지1", 
										50000, 2, 4, 14, 12, 
										new HashSet<String>(Arrays.asList("바다", "산/숲")), 
										new HashSet<String>(Arrays.asList("화장실", "주차")), 
										new HashSet<String>(Arrays.asList("캠핑", "키즈")));
		
		CampsiteDto campsiteDto2 = new CampsiteDto(null, "권상우", "테스트 캠핑장2", "테스트 캠핑장 소개2", 
										"테스트 캠핑장 주소2", "테스트 캠핑장 번호2", "대표 이미지2", 
										50000, 2, 4, 14, 12, 
										new HashSet<String>(Arrays.asList("강", "계곡")), 
										new HashSet<String>(Arrays.asList("바베큐장", "수영장")), 
										new HashSet<String>(Arrays.asList("차박", "캠핑")));
		testCampsite1 = campsiteDto1.toEntity();
		testCampsite1.setMember(testMember1);
		testCampsite2 = campsiteDto2.toEntity();
		testCampsite2.setMember(testMember2);
		testCampsite1 = campsiteRepository.save(testCampsite1);
		testCampsite2 = campsiteRepository.save(testCampsite2);
	}
	
	@Test
	public void findByMemberNoAndCampsiteId() {
		// given
		FavoriteCampsite favoriteCampsite = new FavoriteCampsite();
		favoriteCampsite.setMember(testMember1);
		favoriteCampsite.setCampsite(testCampsite2);
		favoriteRepository.save(favoriteCampsite);
		
		// when
		FavoriteCampsite result = favoriteRepository.findByMember_MemberNoAndCampsite_CampsiteId(
											testMember1.getMemberNo(), testCampsite2.getCampsiteId()).get();
		
		// then
		assertThat(result).isEqualTo(favoriteCampsite);
	}

}
