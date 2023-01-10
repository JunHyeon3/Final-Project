package himedia.campus.service;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.campus.dto.ReservationDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.member.Member;
import himedia.campus.entity.reservation.Reservation;
import himedia.campus.entity.reservation.ReservationStatus;
import himedia.campus.repository.MemberRepository;
import himedia.campus.repository.ReservationRepository;
import himedia.campus.repository.campsite.CampsiteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final CampsiteRepository campsiteRepository;
	private final MemberRepository memberRepository;
	
	public Reservation saveReservation(ReservationDto reservationDto, Long campsiteId, String memberId) {
		reservationDto.setReservationDate(LocalDateTime.now());
		reservationDto.setReservationStatus(ReservationStatus.WAITING);
		Reservation reservation = reservationDto.toEntity();
		
		Campsite findCampsite = campsiteRepository.findByCampsiteId(campsiteId).get();
		Member findMember = memberRepository.findByMemberId(memberId).get();
		reservation.setCampsite(findCampsite);
		reservation.setMember(findMember);
		
		reservationRepository.save(reservation);
		
		return reservation;
	}
	
	public Optional<Reservation> findByReservationId(Long reservationId) {
		return reservationRepository.findByReservationId(reservationId);
	}
	
	public void updateReservationStatus(Long reservationId, ReservationStatus reservationStatus) {
		Reservation reservation = reservationRepository.findByReservationId(reservationId).get();
		reservation.updateReservationStatus(reservationStatus);
	}

}
