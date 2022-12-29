package himedia.campus.reservation.service;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.campus.campsite.entity.Campsite;
import himedia.campus.campsite.repository.CampsiteRepository;
import himedia.campus.member.entity.Member;
import himedia.campus.member.repository.MemberRepository;
import himedia.campus.reservation.dto.ReservationDto;
import himedia.campus.reservation.entity.Reservation;
import himedia.campus.reservation.entity.ReservationStatus;
import himedia.campus.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final CampsiteRepository campsiteRepository;
	private final MemberRepository memberRepository;
	
	public Long saveReservation(ReservationDto reservationDto, Long campsiteId, String memberId) {
		reservationDto.setReservationDate(LocalDateTime.now());
		reservationDto.setReservationStatus(ReservationStatus.WAITING);
		Reservation reservation = reservationDto.toEntity();
		
		Campsite findCampsite = campsiteRepository.findByCampsiteId(campsiteId).get();
		Member findMember = memberRepository.findByMemberId(memberId).get();
		reservation.setCampsite(findCampsite);
		reservation.setMember(findMember);
		
		reservationRepository.save(reservation);
		
		return reservation.getReservationId();
	}
	
}
