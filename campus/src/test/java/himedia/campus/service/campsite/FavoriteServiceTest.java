package himedia.campus.service.campsite;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

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
import himedia.campus.repository.campsite.CampsiteRepository;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
class FavoriteServiceTest {

	@Autowired FavoriteService favoriteService;
	@Autowired MemberRepository memberRepository;
	@Autowired CampsiteRepository campsiteRepository;
	@Autowired PasswordEncoder encoder;
	
	private Member testMember;
	
	private Campsite testCampsite;
	
	@BeforeAll
	public void createTestCampsite() {
		MemberDto memberDto = new MemberDto(MemberRole.ADMIN, "user", "asdfasdf", 
										"이미자", "010-1111-1111", 22);
		testMember = memberRepository.save(Member.create(memberDto, encoder));
		
		CampsiteDto campsiteDto = new CampsiteDto(null, "이미자", "테스트 캠핑장1", "테스트 캠핑장 소개1", 
										"테스트 캠핑장 주소1", "테스트 캠핑장 번호1", "대표 이미지1", 
										50000, 2, 4, 14, 12, 
										new HashSet<String>(Arrays.asList("바다", "산/숲")), 
										new HashSet<String>(Arrays.asList("화장실", "주차")), 
										new HashSet<String>(Arrays.asList("캠핑", "키즈")));
		testCampsite = campsiteDto.toEntity();
		testCampsite.setMember(testMember);
		testCampsite = campsiteRepository.save(testCampsite);
	}
	
	@Test
	public void addFavoriteCampsite() {
		// given
		String memberId = testMember.getMemberId();
		Long memberNo = testMember.getMemberNo();
		Long campsiteId = testCampsite.getCampsiteId();
		
		// when
		FavoriteCampsite result = favoriteService.addFavoriteCampsite(memberId, campsiteId);
		
		// then
		assertThat(result).isNotNull();
		assertThat(result.getMember().getMemberNo()).isEqualTo(memberNo);
		assertThat(result.getCampsite().getCampsiteId()).isEqualTo(campsiteId);
	}
	
	@Test
	public void deleteFavoriteCampsite() {
		// given
		String memberId = testMember.getMemberId();
		Long memberNo = testMember.getMemberNo();
		Long campsiteId = testCampsite.getCampsiteId();
		favoriteService.addFavoriteCampsite(memberId, campsiteId);
		
		// when
		favoriteService.deleteFavoriteCampsite(memberId, campsiteId);
		Optional<FavoriteCampsite> result = favoriteService.findByMemberNoAndCampsiteId(memberNo, campsiteId);
		
		// then
		assertThat(result).isEmpty();
	}
	
	@Test
	public void findByMemberNoAndCampsiteId() {
		// given
		String memberId = testMember.getMemberId();
		Long memberNo = testMember.getMemberNo();
		Long campsiteId = testCampsite.getCampsiteId();
		favoriteService.addFavoriteCampsite(memberId, campsiteId);
		
		// when
		FavoriteCampsite result = favoriteService.findByMemberNoAndCampsiteId(memberNo, campsiteId).get();
		
		// then
		assertThat(result).isNotNull();
		assertThat(result.getMember().getMemberNo()).isEqualTo(memberNo);
		assertThat(result.getCampsite().getCampsiteId()).isEqualTo(campsiteId);
	}
	
}
