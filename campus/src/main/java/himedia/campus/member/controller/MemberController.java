package himedia.campus.member.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.campus.member.dto.MemberDto;
import himedia.campus.member.entity.Member;
import himedia.campus.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	private final PasswordEncoder encoder;
	
	@GetMapping("/join")
	public String joinForm(Model model) {
		model.addAttribute("memberDto", new MemberDto());
		return "member/join";
	}
	
	@PostMapping("/join")
	public String join(@Validated MemberDto memberDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "member/join";
		}
		try {
			memberService.saveMember(Member.create(memberDto, encoder));
		} catch (IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			return "member/join";
		}
		return "redirect:/member/login";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	
}
