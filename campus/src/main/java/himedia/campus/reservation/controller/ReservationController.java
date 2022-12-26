package himedia.campus.reservation.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import himedia.campus.campsite.entity.Campsite;
import himedia.campus.campsite.service.CampsiteService;
import himedia.campus.member.entity.Member;
import himedia.campus.member.service.MemberService;
import himedia.campus.reservation.dto.ReservationDto;
import himedia.campus.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReservationController {
	
	private final ReservationService reservationService;
	private final CampsiteService campsiteService;
	private final MemberService memberService;
	
	@GetMapping("/reservation/{campsiteId}")
	public String reservationForm(@PathVariable Long campsiteId, Model model, Principal principal) {
		if(principal != null) {
			Campsite resevationCampsite = campsiteService.findByCampsiteId(campsiteId).get();
			model.addAttribute("resevationCampsite", resevationCampsite);
			model.addAttribute("reservationDto", new ReservationDto());
			return "reservation/reservation";
		}
		else {
			return "redirect:/campsites/" + campsiteId;
		}
	}
	
	@PostMapping("/reservation/{campsiteId}")
	public String reservationNew(@PathVariable Long campsiteId, Principal principal, ReservationDto reservationDto) {
		
		reservationService.saveReservation(reservationDto, campsiteId, principal.getName());
		
		return "redirect:/";
	}

//	@GetMapping("/reservation/{memberNo}")
//	public String userReservaion() {
//		return "reservation/reservation";
//	}
//	
//	@GetMapping("/admin/reservation/{memberNo}")
//	public String adminReservaion() {
//		return "reservation/reservation";
//	}
	
}
