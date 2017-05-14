package kr.ac.hansung.cse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.model.ShippingAddress;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService; // bean으로 등록된 UserService를 DI

	@RequestMapping("/register")
	public String registerUser(Model model) {

		User user = new User();
		ShippingAddress shippingAddress = new ShippingAddress();

		// bi-direction
		user.setShippingAddress(shippingAddress);
		shippingAddress.setUser(user);

		user.setUsername("testName");
		model.addAttribute("user", user); // model에 user저장

		return "registerUser";
	}
}
