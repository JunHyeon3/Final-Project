package himedia.campus.service.campsite;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.dto.MemberDto;
import himedia.campus.dto.campsite.CampsiteDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.member.Member;
import himedia.campus.entity.member.MemberRole;
import himedia.campus.repository.MemberRepository;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
class CampsiteServiceTest {

	@Autowired CampsiteService campsiteService;
	@Autowired MemberRepository memberRepository;
	@Autowired PasswordEncoder encoder;
	
	private Member testMember1;
	private Member testMember2;
	
	private CampsiteDto campsiteDto1;
	private CampsiteDto campsiteDto2;
	
	@BeforeAll
	public void createTestCampsite() {
		MemberDto dto1 = new MemberDto(MemberRole.ADMIN, "user1", "asdfasdf", 
										"이미자", "010-1111-1111", 22);
		MemberDto dto2 = new MemberDto(MemberRole.ADMIN, "user2", "asdfasdf", 
										"권상우", "010-1111-1111", 22);
		testMember1 = memberRepository.save(Member.create(dto1, encoder));
		testMember2 = memberRepository.save(Member.create(dto2, encoder));
		
		campsiteDto1 = new CampsiteDto(null, "이미자", "테스트 캠핑장1", "테스트 캠핑장 소개1", 
										"테스트 캠핑장 주소1", "테스트 캠핑장 번호1", "대표 이미지1", 
										50000, 2, 4, 14, 12, 
										new HashSet<String>(Arrays.asList("바다", "산/숲")), 
										new HashSet<String>(Arrays.asList("화장실", "주차")), 
										new HashSet<String>(Arrays.asList("캠핑", "키즈")));
		campsiteDto2 = new CampsiteDto(null, "권상우", "테스트 캠핑장2", "테스트 캠핑장 소개2", 
										"테스트 캠핑장 주소2", "테스트 캠핑장 번호2", "대표 이미지2", 
										50000, 2, 4, 14, 12, 
										new HashSet<String>(Arrays.asList("강", "계곡")), 
										new HashSet<String>(Arrays.asList("바베큐장", "수영장")), 
										new HashSet<String>(Arrays.asList("차박", "캠핑")));
	}
	
	@Test
	public void findByThemeAndEnvironment() throws Exception {
		// given
		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "campsite_id");
		Campsite testCampsite1 = campsiteService.saveCampsite(campsiteDto1, testMember1, new ArrayList<MultipartFile>());
		Campsite testCampsite2 = campsiteService.saveCampsite(campsiteDto2, testMember2, new ArrayList<MultipartFile>());
		
		// when
		Page<Campsite> resultList = campsiteService.findByThemeAndEnvironment("차박", "", pageable);
		
		//then
		assertThat(resultList.getContent()).contains(testCampsite2);
		assertThat(resultList.getContent()).doesNotContain(testCampsite1);
		
	}

}
