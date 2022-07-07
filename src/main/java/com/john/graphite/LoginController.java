package com.john.graphite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.john.graphite.model.User;

@Controller
public class LoginController {

	@GetMapping("/myLogin")
	public String getLoginPage(Model model) {
		System.out.println("Inside GET Method /myLogin");
		model.addAttribute("user",new User());
		return "login";
	} 
	
	@PostMapping("/processLogin")
	public String processLogin(@ModelAttribute("user") User user) {
		System.out.println(user);
		return "login";
	}
}
