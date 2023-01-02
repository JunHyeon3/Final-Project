package himedia.campus.campsite.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import himedia.campus.campsite.service.FavoriteService;
import himedia.campus.campsite.vo.CampsiteEnvironment;
import himedia.campus.campsite.vo.CampsiteFacilitie;
import himedia.campus.campsite.vo.CampsiteTheme;
import himedia.campus.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CampsiteController {
	
	private final CampsiteService campsiteService;
	private final CampsiteImgService campsiteImgService;
	private final FavoriteService favoriteService;
	private final MemberService memberService;

	@GetMapping("/campsites")
	public String campsiteList(@PageableDefault(sort="campsiteId", direction = Sort.Direction.DESC) Pageable pageable, 
								@RequestParam(required = false) String campsiteEnvironment,
								@RequestParam(required = false) String campsiteTheme,
								Model model, Principal principal) {
		Page<Campsite> campsiteList = null;
		if(campsiteEnvironment == null && campsiteTheme == null) {
			campsiteList = campsiteService.pageList(pageable);
		}
		else {	
			campsiteList = campsiteService.findByEnviornmentAndTheme(campsiteEnvironment, campsiteTheme, pageable);
		}
		
		int nowPage = campsiteList.getPageable().getPageNumber()+1;                                                                                                                                                                                                                                                                                                                                                                                                               ;
		int startPage = 1;
		int endPage = campsiteList.getTotalPages();
		if(campsiteList.getTotalPages() >= 10) {
			startPage =  Math.max(nowPage-5, 1);
			endPage = Math.min(nowPage+4, campsiteList.getTotalPages());
			if(nowPage < 7) {
				endPage = 10;
			} 
		}
		
        model.addAttribute("campsites", campsiteList);
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
	
	@ModelAttribute("campsiteEnvironments")
	public List<CampsiteEnvironment> campsiteEnvironments() {
		List<CampsiteEnvironment> campsiteEnvironments = new ArrayList<>();
		campsiteEnvironments.add(new CampsiteEnvironment("MOUNTAIN", "산/숲"));
		campsiteEnvironments.add(new CampsiteEnvironment("SEA", "바다"));
		campsiteEnvironments.add(new CampsiteEnvironment("VALLEY", "계곡"));
		campsiteEnvironments.add(new CampsiteEnvironment("RIVER", "강"));
		campsiteEnvironments.add(new CampsiteEnvironment("CITY", "도심"));
		campsiteEnvironments.add(new CampsiteEnvironment("ISLAND", "섬"));
		return campsiteEnvironments;
	}
	
	@ModelAttribute("campsiteThemes")
	public List<CampsiteTheme> campsiteThemes() {
		List<CampsiteTheme> campsiteThemes = new ArrayList<>();
		campsiteThemes.add(new CampsiteTheme("CAMPING", "캠핑"));
		campsiteThemes.add(new CampsiteTheme("GLAMPING", "글램핑"));
		campsiteThemes.add(new CampsiteTheme("CARAVAN", "카라반"));
		campsiteThemes.add(new CampsiteTheme("CAR", "차박"));
		campsiteThemes.add(new CampsiteTheme("PETS", "반려동물"));
		campsiteThemes.add(new CampsiteTheme("KIDS", "키즈"));
		campsiteThemes.add(new CampsiteTheme("FAMILY", "가족"));
		campsiteThemes.add(new CampsiteTheme("COUPLE", "연인"));
		return campsiteThemes;
	}
	
	@ModelAttribute("campsiteFacilities")
	public List<CampsiteFacilitie> campsiteFacilities() {
		List<CampsiteFacilitie> campsiteFacilities = new ArrayList<>();
		campsiteFacilities.add(new CampsiteFacilitie("TOILET", "화장실"));
		campsiteFacilities.add(new CampsiteFacilitie("SHOWER", "샤워실"));
		campsiteFacilities.add(new CampsiteFacilitie("BARBECUE", "바베큐장"));
		campsiteFacilities.add(new CampsiteFacilitie("PARKING", "개별 주차"));
		campsiteFacilities.add(new CampsiteFacilitie("WARMER", "온난방기"));
		campsiteFacilities.add(new CampsiteFacilitie("STORE", "매점"));
		campsiteFacilities.add(new CampsiteFacilitie("POOL", "수영장"));
		campsiteFacilities.add(new CampsiteFacilitie("LENTAL", "장비대여"));
		campsiteFacilities.add(new CampsiteFacilitie("TABLEWARE", "식기류"));
		campsiteFacilities.add(new CampsiteFacilitie("BEDDING", "침구류"));
		return campsiteFacilities;
	}
	
}
