package com.blog.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.app.entity.Member;
import com.blog.app.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	private MemberService memberService;

	@ModelAttribute("member")
	public Member constructMember() {
		return new Member();
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("member") Member member, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "register";
		}

		memberService.saveMember(member);

		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/register";
	}

	@RequestMapping("/register/available")
	@ResponseBody
	public String availableMember(@RequestParam String username) {
		Boolean available = memberService.findByUsername(username) == null;
		return available.toString();
	}

}
