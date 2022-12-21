package himedia.campus.campsite.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.campsite.dto.CampsiteDto;
import himedia.campus.campsite.service.CampsiteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CampsiteController {
	
	private final CampsiteService campsiteService;
	
	@GetMapping("/admin/campsite/new")
	public String campsiteForm(Model model) {
		model.addAttribute("campsiteDto", new CampsiteDto());
		return "campsite/campsiteForm";
	}
	
	@PostMapping("/admin/campsite/new")
	public String campsiteNew(CampsiteDto campsiteDto, Model model,
								@RequestParam List<String> campsiteEnvironment,
								@RequestParam List<String> campsiteFacilitie, 
								@RequestParam List<String> campsiteTheme,
								@RequestParam List<MultipartFile> campsiteImgFiles) {
		try {
			campsiteService.saveCampsite(campsiteDto, campsiteEnvironment, campsiteFacilitie, campsiteTheme, campsiteImgFiles);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "campsite/campsiteForm";
		}
		
		return "redirect:/";
	}
	
}
