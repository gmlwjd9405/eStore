package kr.ac.hansung.cse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.CartDao;
import kr.ac.hansung.cse.model.Cart;

@Service
public class CartService {
	
	@Autowired
	private CartDao cartDao;

	/** cartId에 해당하는 cart를 반환하는 메서드 */
	public Cart getCartById(int cartId) {
		return cartDao.getCartById(cartId);
	}

}
