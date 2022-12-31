package himedia.campus.campsite.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.campsite.dto.CampsiteDto;
import himedia.campus.campsite.entity.Campsite;
import himedia.campus.campsite.entity.CampsiteImg;
import himedia.campus.campsite.service.CampsiteImgService;
import himedia.campus.campsite.service.CampsiteService;
import himedia.campus.member.entity.Member;
import himedia.campus.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CampsiteController {
	
	private final CampsiteService campsiteService;
	private final CampsiteImgService campsiteImgService;
	private final MemberService memberService;

	@GetMapping("/campsites")
	public String campsiteList(Model model) {
		List<Campsite> findCampsites = campsiteService.findAllCampsite();
		model.addAttribute("campsites", findCampsites);
		
		return "campsite/campsites";
	}

	@GetMapping("/campsites/{campsiteId}")
	public String campsiteDetail(@PathVariable Long campsiteId, Model model) {
		model.addAttribute("campsite", campsiteService.findByCampsiteId(campsiteId).get());
		model.addAttribute("campsiteImgs", campsiteImgService.findAllCampsiteImgPath(campsiteId));
		return "campsite/campsite";
	}
	
	@GetMapping("/admin/campsites")
	public String campsiteForm(Principal principal, Model model) {
		Campsite existCampsite = memberService.findByMemberId(principal.getName()).get().getCampsite();
		
		if(existCampsite == null) {
			model.addAttribute("campsiteDto", new CampsiteDto());
			return "campsite/campsiteForm";
		}
		else {
			model.addAttribute("existCampsite", Campsite.toDto(existCampsite));
			return "campsite/campsiteEdit";
		}
	}
	
	@PostMapping("/admin/campsites/new")
	public String campsiteNew(CampsiteDto campsiteDto,
								@RequestParam List<String> campsiteEnvironment,
								@RequestParam List<String> campsiteFacilitie, 
								@RequestParam List<String> campsiteTheme,
								@RequestParam List<MultipartFile> campsiteImgFiles,
								Model model, Principal principal) {
		try {
			campsiteService.saveCampsite(campsiteDto, principal.getName(), campsiteEnvironment, campsiteFacilitie, campsiteTheme, campsiteImgFiles);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "캠핑장 등록 중 에러가 발생하였습니다.");
            return "campsite/campsiteForm";
		}
		
		return "redirect:/";
	}
	
	@PutMapping("/admin/campsites/{campsiteId}")
	public String campsiteEdit(CampsiteDto campsiteDto,
								@RequestParam List<String> campsiteEnvironment,
								@RequestParam List<String> campsiteFacilitie, 
								@RequestParam List<String> campsiteTheme,
								@RequestParam List<MultipartFile> campsiteImgFiles) throws Exception {
		campsiteService.updateCampsite(campsiteDto, campsiteEnvironment, campsiteFacilitie, campsiteTheme, campsiteImgFiles);
		return "redirect:/";
	}
	
	@DeleteMapping("/admin/campsites/{campsiteId}")
	public String campsiteDelete(@PathVariable Long campsiteId) throws Exception {
		Campsite findCampsite = campsiteService.findByCampsiteId(campsiteId).get();
		List<CampsiteImg> findCampsiteImg = campsiteImgService.findByCampsiteId(campsiteId);
		
		campsiteService.deleteCampsite(findCampsite, findCampsiteImg);
		return "redirect:/";
	}
	
	
}
