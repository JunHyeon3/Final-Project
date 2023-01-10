package himedia.campus.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import himedia.campus.dto.MemberDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.member.Member;
import himedia.campus.entity.reservation.Reservation;
import himedia.campus.entity.reservation.ReservationStatus;
import himedia.campus.service.MemberService;
import himedia.campus.service.ReservationService;
import himedia.campus.service.campsite.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MypageController {
	
	private final MemberService memberService;
	private final ReservationService reservationService;
	private final FavoriteService favoriteService;
	
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
		if(principal == null) {
			return "member/login";
		}
		
		Member member = memberService.findByMemberId(principal.getName()).get();
		model.addAttribute("memberDto", Member.toDto(member));
		return "mypage/my-info-edit";
	}

	@PutMapping("/my-page/put/my-info")
	public String myInfoEdit(MemberDto memberDto) {
		log.info("@@: "+ memberDto.getMemberName());
		log.info("@@: "+ memberDto.getMemberPhone());
		log.info("@@: "+ memberDto.getMemberAge());
		memberService.updateMember(memberDto);
		return "redirect:/my-page";
	}

	@DeleteMapping("/my-page/delete/my-info")
	public String myInfoDelete(Principal principal) {
		if(principal == null) {
			return "member/login";
		}
		
		Member member = memberService.findByMemberId(principal.getName()).get();
		memberService.deleteMember(member);
		return "redirect:/member/logout";
	}
	
	@GetMapping(value = {"/my-page/reservation", "/admin/my-page/reservation"})
	public String userReservationList(Principal principal, Model model) {
		if(principal == null) {
			return "member/login";
		}
		
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
	
	@GetMapping("/my-page/favorite")
	public String favoriteList(Principal principal, Model model) {
		if(principal == null) {
			return "member/login";
		}
		
		Member member = memberService.findByMemberId(principal.getName()).get();
		List<Campsite> campsites = member.getFavoriteCampsite().stream().map(t -> t.getCampsite()).toList();
		model.addAttribute("favoriteCampsites", campsites);
		return "mypage/my-favorite";
	}
	
	@DeleteMapping("/my-page/favorite/{campsiteId}")
	public String favoriteDelete(@PathVariable Long campsiteId, Principal principal) {
		if(principal == null) {
			return "member/login";
		}
		
		favoriteService.deleteFavoriteCampsite(principal.getName(), campsiteId);
		return "redirect:/my-page/favorite";
	}
	
}
