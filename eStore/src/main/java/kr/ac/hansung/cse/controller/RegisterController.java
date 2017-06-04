package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Cart;
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

		user.setShippingAddress(shippingAddress);
		// shippingAddress.setUser(user); // bi-direction

		user.setUsername("testName");
		model.addAttribute("user", user); // model에 user저장

		return "registerUser";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserPost(@Valid User user, BindingResult result, Model model) {

		// 입력한 값이 잘못되었으면 다시 입력받도록 registerUser로 이동
		if (result.hasErrors()) {
			return "registerUser";
		}

		// 이미 있는 아이디로 회원가입하는 예외상황 처리
		List<User> userList = userService.getAllUsers(); // DB조회
		for (int i = 0; i < userList.size(); i++) {
			if (user.getUsername().equals(userList.get(i).getUsername())) {
				model.addAttribute("usernameMsg", "Usename already exists");

				return "registerUser";
			}
		}

		// 사용자가 입력하지 않은 enable과 authority 설정
		user.setEnabled(true);

		if (user.getUsername().equals("admin"))
			user.setAuthority("ROLE_ADMIN");
		else
			user.setAuthority("ROLE_USER");

		// user.getShippingAddress().setUser(user); // bi-direction

		Cart newCart = new Cart();
		user.setCart(newCart);

		userService.addUser(user); // DB에 저장

		return "registerUserSuccess";
	}
}
