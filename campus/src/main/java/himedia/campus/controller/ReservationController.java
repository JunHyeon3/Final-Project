package himedia.campus.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import himedia.campus.dto.ReservationDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.service.ReservationService;
import himedia.campus.service.campsite.CampsiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReservationController {
	
	private final ReservationService reservationService;
	private final CampsiteService campsiteService;
	
	@GetMapping("/reservation/{campsiteId}")
	public String reservationForm(@PathVariable Long campsiteId, Model model, Principal principal) {
		if(principal == null) {
			return "member/login";
		}
		
		Campsite reservationCampsite = campsiteService.findByCampsiteId(campsiteId).get();
		model.addAttribute("reservationCampsite", reservationCampsite);
		model.addAttribute("reservationDto", new ReservationDto());
		return "reservation/reservation";
	}
	
	@PostMapping("/reservation/{campsiteId}")
	public String reservationNew(@Validated ReservationDto reservationDto, BindingResult bindingResult, 
								@PathVariable Long campsiteId, Principal principal, Model model) {
		if(bindingResult.hasErrors()) {
			Campsite reservationCampsite = campsiteService.findByCampsiteId(campsiteId).get();
			model.addAttribute("reservationCampsite", reservationCampsite);
			return "reservation/reservation";
		}
		reservationService.saveReservation(reservationDto, campsiteId, principal.getName());
		
		return "redirect:/";
	}
	
}
