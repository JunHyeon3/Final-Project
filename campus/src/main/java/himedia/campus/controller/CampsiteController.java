package himedia.campus.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.dto.campsite.CampsiteDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.campsite.CampsiteImg;
import himedia.campus.entity.member.Member;
import himedia.campus.service.MemberService;
import himedia.campus.service.campsite.CampsiteImgService;
import himedia.campus.service.campsite.CampsiteService;
import himedia.campus.service.campsite.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CampsiteController {
	
	private final CampsiteService campsiteService;
	private final CampsiteImgService campsiteImgService;
	private final FavoriteService favoriteService;
	private final MemberService memberService;

	@GetMapping("/campsites")
	public String campsiteList(@PageableDefault(sort="campsiteId", direction = Sort.Direction.DESC) Pageable pageable, 
								@RequestParam(required = false) String searchEnvironment,
								@RequestParam(required = false) String searchTheme,
								Model model, Principal principal) {
		Page<Campsite> campsiteList = null;
		if(searchEnvironment == null && searchTheme == null) {
			campsiteList = campsiteService.pageList(pageable);
		}
		else {	
			campsiteList = campsiteService.findByEnviornmentAndTheme(searchEnvironment, searchTheme, pageable);
		}
		
		int nowPage = campsiteList.getPageable().getPageNumber()+1;                                                                                                                                                                                                                                                                                                                                                                                                               ;
		int startPage = 1;
		int endPage = campsiteList.getTotalPages();
		if(campsiteList.getTotalPages() >= 9) {
			startPage =  Math.max(nowPage-5, 0);
			endPage = Math.min(nowPage+4, campsiteList.getTotalPages());
			if(nowPage < 7) {
				endPage = 10;
			} 
		}
		
        model.addAttribute("campsites", campsiteList);
        model.addAttribute("searchEnv", searchEnvironment);
        model.addAttribute("searchTheme", searchTheme);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		
		if(principal != null) {
			Long memberNo = memberService.findByMemberId(principal.getName()).get().getMemberNo();
			model.addAttribute("memberNo", memberNo);
		}

		return "campsite/campsites";
	}
	
	public boolean find(Long memberNo, Long campsiteId) {
		return !favoriteService.findByMemberNoAndCampsiteId(memberNo, campsiteId).isEmpty();
	}

	@GetMapping("/campsites/{campsiteId}")
	public String campsiteDetail(@PathVariable Long campsiteId, Model model) {
		model.addAttribute("campsite", campsiteService.findByCampsiteId(campsiteId).get());
		model.addAttribute("campsiteImgs", campsiteImgService.findAllCampsiteImgPath(campsiteId));
		return "campsite/campsite";
	}
	
	@PostMapping("/campsites/favorite/{campsiteId}")
	public String favoriteAdd(@PathVariable Long campsiteId, Principal principal) {
		Member member = memberService.findByMemberId(principal.getName()).get();
		Campsite campsite = campsiteService.findByCampsiteId(campsiteId).get();
		
		favoriteService.addFavoriteCampsite(member, campsite);
		return "redirect:/campsites";
	}
	
	@DeleteMapping("/campsites/favorite/{campsiteId}")
	public String favoriteDelete(@PathVariable Long campsiteId, Principal principal) {
		Member member = memberService.findByMemberId(principal.getName()).get();
		
		favoriteService.deleteFavoriteCampsite(member.getMemberNo(), campsiteId);
		return "redirect:/campsites";
	}
	
	@GetMapping("/admin/campsites")
	public String campsiteForm(Principal principal, Model model) {
		Campsite existCampsite = memberService.findByMemberId(principal.getName()).get().getCampsite();
		
		if(existCampsite == null) {
			model.addAttribute("campsiteDto", new CampsiteDto());
			return "campsite/campsite-add";
		}
		else {
			model.addAttribute("existCampsite", Campsite.toDto(existCampsite));
			return "campsite/campsite-edit";
		}
	}
	
	@PostMapping("/admin/campsites/new")
	public String campsiteNew(@Validated CampsiteDto campsiteDto, BindingResult bindingResult,
								@RequestParam(required = false) List<String> campsiteEnvironment,
								@RequestParam(required = false) List<String> campsiteFacilitie, 
								@RequestParam(required = false) List<String> campsiteTheme,
								@RequestParam List<MultipartFile> campsiteImgFiles,
								Model model, Principal principal) throws Exception {
		if(bindingResult.hasErrors()) {
			return "/campsite/campsite-add";
		}
		
		campsiteService.saveCampsite(campsiteDto, principal.getName(), campsiteEnvironment, campsiteFacilitie, campsiteTheme, campsiteImgFiles);
		
		return "redirect:/";
	}
	
	@PutMapping("/admin/campsites/{campsiteId}")
	public String campsiteEdit(CampsiteDto campsiteDto, 
								@RequestParam List<MultipartFile> campsiteImgFiles) throws Exception {
		campsiteService.updateCampsite(campsiteDto, campsiteImgFiles);
		return "redirect:/";
	}
	
	@DeleteMapping("/admin/campsites/{campsiteId}")
	public String campsiteDelete(@PathVariable Long campsiteId) throws Exception {
		Campsite findCampsite = campsiteService.findByCampsiteId(campsiteId).get();
		List<CampsiteImg> findCampsiteImg = campsiteImgService.findByCampsiteId(campsiteId);
		
		campsiteService.deleteCampsite(findCampsite, findCampsiteImg);
		return "redirect:/";
	}
	
	@ModelAttribute("campsiteEnvironments")
	public List<String> campsiteEnvironments() {
		List<String> campsiteEnvironments = new ArrayList<>();
		campsiteEnvironments.add("산/숲");
		campsiteEnvironments.add("바다");
		campsiteEnvironments.add("계곡");
		campsiteEnvironments.add("강");
		campsiteEnvironments.add("도심");
		campsiteEnvironments.add("섬");
		return campsiteEnvironments;
	}
	
	@ModelAttribute("campsiteThemes")
	public List<String> campsiteThemes() {
		List<String> campsiteThemes = new ArrayList<>();
		campsiteThemes.add("캠핑");
		campsiteThemes.add("글램핑");
		campsiteThemes.add("카라반");
		campsiteThemes.add("차박");
		campsiteThemes.add("반려동물");
		campsiteThemes.add("키즈");
		campsiteThemes.add("가족");
		campsiteThemes.add("연인");
		return campsiteThemes;
	}
	
	@ModelAttribute("campsiteFacilities")
	public List<String> campsiteFacilities() {
		List<String> campsiteFacilities = new ArrayList<>();
		campsiteFacilities.add("화장실");
		campsiteFacilities.add("샤워실");
		campsiteFacilities.add("바베큐장");
		campsiteFacilities.add("개별 주차");
		campsiteFacilities.add("온난방기");
		campsiteFacilities.add("매점");
		campsiteFacilities.add("수영장");
		campsiteFacilities.add("장비대여");
		campsiteFacilities.add("식기류");
		campsiteFacilities.add("침구류");
		return campsiteFacilities;
	}
	
}
