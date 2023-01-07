package himedia.campus.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.dto.campsite.CampsiteDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.campsite.CampsiteImg;
import himedia.campus.entity.member.Member;
import himedia.campus.repository.campsite.CampsiteRepository;
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
	private final CampsiteRepository campsiteRepository;

	@GetMapping("/campsites")
	public String campsiteList(@PageableDefault(direction = Sort.Direction.DESC) Pageable pageable, 
								@RequestParam(required = false) String searchEnvironment,
								@RequestParam(required = false) String searchTheme,
								Model model, Principal principal) {
		log.info("환경 : " + searchEnvironment);
		log.info("테마 : " + searchTheme);
		Page<Campsite> campsiteList = null;
		if(searchEnvironment == null && searchTheme == null) {
			campsiteList = campsiteService.pageList(pageable);
		}
		else {	
			campsiteList = campsiteService.findByThemeAndEnvironment(searchTheme, searchEnvironment, pageable);
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
								@RequestParam List<MultipartFile> campsiteImgFiles,
								Model model, Principal principal) throws Exception {
	
		if(bindingResult.hasErrors()) {
			return "/campsite/campsite-add";
		}
		
		Member member = memberService.findByMemberId(principal.getName()).get();
		
		campsiteService.saveCampsite(campsiteDto, member, campsiteImgFiles);
		
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
	
	@ModelAttribute("environments")
	public Set<String> environments() {
		Set<String> environments = new HashSet<>();
		environments.add("산/숲");
		environments.add("바다");
		environments.add("계곡");
		environments.add("강");
		environments.add("도심");
		environments.add("섬");
		return environments;
	}
	
	@ModelAttribute("themes")
	public Set<String> themes() {
		Set<String> themes = new HashSet<>();
		themes.add("캠핑");
		themes.add("글램핑");
		themes.add("카라반");
		themes.add("차박");
		themes.add("반려동물");
		themes.add("키즈");
		themes.add("가족");
		themes.add("연인");
		return themes;
	}
	
	@ModelAttribute("facilities")
	public Set<String> facilities() {
		Set<String> facilities = new HashSet<>();
		facilities.add("화장실");
		facilities.add("샤워실");
		facilities.add("바베큐장");
		facilities.add("개별 주차");
		facilities.add("온난방기");
		facilities.add("매점");
		facilities.add("수영장");
		facilities.add("장비대여");
		facilities.add("식기류");
		facilities.add("침구류");
		return facilities;
	}
	
//	@GetMapping("/ss")
//	@ResponseBody
//	public Set<String> ff() {
//		return campsiteRepository.fff();
//	}
}
