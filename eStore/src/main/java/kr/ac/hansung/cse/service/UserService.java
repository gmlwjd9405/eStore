package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.UserDao;
import kr.ac.hansung.cse.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/** user�� �߰��ϴ� �޼��� */
	public void addUser(User user) {
		userDao.addUser(user);
	}

	/** ������ id�� �´� user�� �������� �޼��� */
	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	/** ��� user���� �������� �޼��� */
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	/** ������ name�� �´� user�� �������� �޼��� */
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

}
