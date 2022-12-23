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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CampsiteController {
	
	private final CampsiteService campsiteService;
	private final CampsiteImgService campsiteImgService;

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
	
	@GetMapping("/admin/campsite/management")
	public String campsiteForm(Principal principal, Model model) {
		Optional<Campsite> existCampsite = campsiteService.findByCampsiteManager(principal.getName());
		if(existCampsite.isEmpty()) {
			model.addAttribute("campsiteDto", new CampsiteDto());
			return "campsite/campsiteForm";
		}
		else {
			model.addAttribute("existCampsite", existCampsite.get());
			return "campsite/campsiteEdit";
		}
	}
	
	@PostMapping("/admin/campsite/new")
	public String campsiteNew(CampsiteDto campsiteDto, Model model,
								Principal principal,
								@RequestParam List<String> campsiteEnvironment,
								@RequestParam List<String> campsiteFacilitie, 
								@RequestParam List<String> campsiteTheme,
								@RequestParam List<MultipartFile> campsiteImgFiles) {
		try {
			campsiteDto.setCampsiteManager(principal.getName());
			campsiteService.saveCampsite(campsiteDto, campsiteEnvironment, campsiteFacilitie, campsiteTheme, campsiteImgFiles);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "campsite/campsiteForm";
		}
		
		return "redirect:/";
	}
	
	@PutMapping("/admin/campsite/management")
	public String campsiteEdit() {
		log.info("수정 실행");
		return "redirect:/";
	}
	
	@DeleteMapping("/admin/campsite/management")
	public String campsiteDelete(Principal principal) throws Exception {
		Campsite findCampsite = campsiteService.findByCampsiteManager(principal.getName()).get();
		List<CampsiteImg> findCampsiteImg = campsiteImgService.findByCampsiteId(findCampsite.getCampsiteId());
		
		campsiteService.deleteCampsite(findCampsite, findCampsiteImg);
		return "redirect:/";
	}
	
	
}
