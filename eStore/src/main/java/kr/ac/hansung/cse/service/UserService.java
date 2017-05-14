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

	/** user를 추가하는 메서드 */
	public void addUser(User user) {
		userDao.addUser(user);
	}

	/** 지정한 id에 맞는 user를 가져오는 메서드 */
	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	/** 모든 user들을 가져오는 메서드 */
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	/** 지정한 name에 맞는 user를 가져오는 메서드 */
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

}
