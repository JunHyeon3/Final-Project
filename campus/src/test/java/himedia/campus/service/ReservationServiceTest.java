package himedia.campus.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
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
import himedia.campus.dto.ReservationDto;
import himedia.campus.dto.campsite.CampsiteDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.member.Member;
import himedia.campus.entity.member.MemberRole;
import himedia.campus.entity.reservation.Reservation;
import himedia.campus.entity.reservation.ReservationStatus;
import himedia.campus.repository.MemberRepository;
import himedia.campus.repository.ReservationRepository;
import himedia.campus.repository.campsite.CampsiteRepository;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
class ReservationServiceTest {

	@Autowired ReservationService reservationService;
	@Autowired ReservationRepository reservationRepository;
	@Autowired CampsiteRepository campsiteRepository;
	@Autowired MemberRepository memberRepository;
	@Autowired PasswordEncoder encoder;
	
	private Member testMember;
	private Campsite testCampsite;
	
	@BeforeAll
	public void createTestCampsite() {
		MemberDto memberDto = new MemberDto(MemberRole.ADMIN, "testUser", "asdfasdf", 
										"이미자", "010-1111-1111", 22);
		testMember = memberRepository.save(Member.create(memberDto, encoder));
		
		CampsiteDto campsiteDto= new CampsiteDto(null, "이미자", "테스트 캠핑장1", "테스트 캠핑장 소개1", 
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
	public void saveReservation() {
		// given
		ReservationDto reservationDto = new ReservationDto(4L, LocalDateTime.now(), 
														"2023-01-08", "2023-01-09", 
														2, 60000, ReservationStatus.WAITING);
		
		// when
		Reservation resultReservation = reservationService.saveReservation(reservationDto, 
																testCampsite.getCampsiteId(), 
																testMember.getMemberId());
		
		// then
		assertThat(resultReservation.getReservationId()).isEqualTo(reservationDto.getReservationId());
	}
	
	@Test
	public void updateReservationStatus() {
		// given
		ReservationDto reservationDto = new ReservationDto(null, LocalDateTime.now(), 
													"2023-01-08", "2023-01-09", 
													2, 60000, ReservationStatus.WAITING);
		Reservation savedReservation = reservationService.saveReservation(reservationDto, 
																testCampsite.getCampsiteId(), 
																testMember.getMemberId());
		
		// when
		reservationService.updateReservationStatus(savedReservation.getReservationId(), ReservationStatus.CANCEL);
		Reservation resultReservation = reservationRepository.findByReservationId(savedReservation.getReservationId()).get();
		
		// then
		assertThat(resultReservation.getReservationStatus()).isEqualTo(ReservationStatus.CANCEL);
	}

}
