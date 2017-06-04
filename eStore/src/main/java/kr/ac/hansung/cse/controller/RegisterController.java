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
	private UserService userService; // bean���� ��ϵ� UserService�� DI

	@RequestMapping("/register")
	public String registerUser(Model model) {

		User user = new User();
		ShippingAddress shippingAddress = new ShippingAddress();

		user.setShippingAddress(shippingAddress);
		// shippingAddress.setUser(user); // bi-direction

		user.setUsername("testName");
		model.addAttribute("user", user); // model�� user����

		return "registerUser";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserPost(@Valid User user, BindingResult result, Model model) {

		// �Է��� ���� �߸��Ǿ����� �ٽ� �Է¹޵��� registerUser�� �̵�
		if (result.hasErrors()) {
			return "registerUser";
		}

		// �̹� �ִ� ���̵�� ȸ�������ϴ� ���ܻ�Ȳ ó��
		List<User> userList = userService.getAllUsers(); // DB��ȸ
		for (int i = 0; i < userList.size(); i++) {
			if (user.getUsername().equals(userList.get(i).getUsername())) {
				model.addAttribute("usernameMsg", "Usename already exists");

				return "registerUser";
			}
		}

		// ����ڰ� �Է����� ���� enable�� authority ����
		user.setEnabled(true);

		if (user.getUsername().equals("admin"))
			user.setAuthority("ROLE_ADMIN");
		else
			user.setAuthority("ROLE_USER");

		// user.getShippingAddress().setUser(user); // bi-direction

		Cart newCart = new Cart();
		user.setCart(newCart);

		userService.addUser(user); // DB�� ����

		return "registerUserSuccess";
	}
}
