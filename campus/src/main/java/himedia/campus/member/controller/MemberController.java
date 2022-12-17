package himedia.campus.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import himedia.campus.member.entity.Member;
import himedia.campus.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

	private final MemberService memberService;
	
	
	@GetMapping("/join")
	public String joinForm(Model model) {
		model.addAttribute("member", new Member());
		return "member/join";
	}
	
	@PostMapping("/join")
	public String join(@Validated Member member, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "redirect:/join";
		}
		
		try {
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "redirect:/join";
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(Model model) {
		
		return "redirect:/";
	}
	
}
