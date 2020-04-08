package com.bursuc.flightreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bursuc.flightreservation.entities.User;
import com.bursuc.flightreservation.repos.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/showRegistration")
	public String showRegistrationPage() {
		return "login/registerUser";
	}
	
	@PostMapping ("/registerUser")
	public String registerUser(@ModelAttribute User user) {
		userRepository.save(user);
		return "login/login";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		return "login/login";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
		User user = userRepository.findByEmail(email);
		if (user.getPassword().equals(password)) {
			return "findFlights";
		}else {
		model.addAttribute("wrong", "Wrong email or password, please try again");
		return "login/login";
		}
	}

}
