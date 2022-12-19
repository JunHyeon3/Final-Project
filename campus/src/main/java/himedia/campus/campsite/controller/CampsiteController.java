package himedia.campus.campsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String campsiteNew(CampsiteDto campsiteDto) {
		campsiteService.saveCampsite(campsiteDto);
		return "redirect:/";
	}
	
}
