package himedia.campus.mypage.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import himedia.campus.campsite.entity.Campsite;
import himedia.campus.member.dto.MemberDto;
import himedia.campus.member.entity.Member;
import himedia.campus.member.entity.MemberRole;
import himedia.campus.member.service.MemberService;
import himedia.campus.reservation.entity.Reservation;
import himedia.campus.reservation.entity.ReservationStatus;
import himedia.campus.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MypageController {
	
	private final MemberService memberService;
	private final ReservationService reservationService;
	
	@GetMapping("/my-page")
	public String myInfo(Model model, Principal principal) {
		if(principal != null) {
			Member member = memberService.findByMemberId(principal.getName()).get();
			model.addAttribute("memberDto", Member.toDto(member));
			return "mypage/my-info";
		}
		else {
			return "member/login";
		}
	}
	
	@GetMapping("/my-page/put/my-info")
	public String myInfoEditForm(Principal principal, Model model) {
		Member member = memberService.findByMemberId(principal.getName()).get();
		model.addAttribute("memberDto", Member.toDto(member));
		return "mypage/my-info-edit";
	}

	@PutMapping("/my-page/put/my-info")
	public String myInfoEdit(MemberDto memberDto) {
		memberService.updateMember(memberDto);
		return "redirect:/my-page";
	}

	@DeleteMapping("/my-page/delete/my-info")
	public String myInfoDelete(Principal principal) {
		Member member = memberService.findByMemberId(principal.getName()).get();
		memberService.deleteMember(member);
		return "redirect:/member/logout";
	}
	
	@GetMapping(value = {"/my-page/reservation", "/admin/my-page/reservation"})
	public String userReservationList(Principal principal, Model model) {
		List<Reservation> reservations = new ArrayList<>();

		Member member = memberService.findByMemberId(principal.getName()).get();
		Campsite campsite = member.getCampsite();
		
		if(member.getMemberRole().name().equals("ADMIN")) {
			if(campsite == null) {
				return "redirect:/admin/campsites";
			}
			reservations =  campsite.getReservations();
        } 
        else {
        	reservations =  member.getReservations();
        }
		
		model.addAttribute("reservations", reservations);
		return "mypage/my-reservation";
	}
	
	@DeleteMapping("/my-page/reservation/{reservationId}")
	public String userReservaionDelete(@PathVariable Long reservationId) {
		reservationService.updateReservationStatus(reservationId, ReservationStatus.CANCEL);
			
		return "redirect:/my-page/reservation";
	}
	
	@PutMapping("/admin/my-page/reservation/{reservationId}")
	public String adminReservaionCancel(@PathVariable Long reservationId) {
		reservationService.updateReservationStatus(reservationId, ReservationStatus.CONFIRM);
		
		return "redirect:/admin/my-page/reservation";
	}
	
	@DeleteMapping("/admin/my-page/reservation/{reservationId}")
	public String adminReservaionDelete(@PathVariable Long reservationId) {
		reservationService.updateReservationStatus(reservationId, ReservationStatus.CANCEL);
		
		return "redirect:/admin/my-page/reservation";
	}
	
}
