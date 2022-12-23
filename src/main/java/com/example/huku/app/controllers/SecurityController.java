package com.example.huku.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.huku.app.service.user.UserRegistrationService;
import com.example.huku.domain.models.user.UserRegistrationForm;

@Controller
public class SecurityController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    
	@GetMapping("/")
	public String showTop() {
		return "user/index";
	}

	@GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("user/registration")
    public String showUserRegistration(@ModelAttribute("form") UserRegistrationForm form) {
		return "signup";
	}

    @PostMapping("/user/registration")
	public String userRegistration(@Valid @ModelAttribute("form") UserRegistrationForm form, BindingResult result) {
		if(result.hasErrors()) {
			return "signup";
		}
		userRegistrationService.userRegistration(form.getUsername(), form.getPassword());
		return "redirect:/login";
	}
}