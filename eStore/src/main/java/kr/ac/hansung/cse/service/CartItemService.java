package kr.ac.hansung.cse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.CartItemDao;
import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;

@Service
public class CartItemService {

	@Autowired
	private CartItemDao cartItemDao;

	/** cartItem을 cart에 담는, 추가하는 메서드 */
	public void addCartItem(CartItem cartItem) {
		cartItemDao.addCartItem(cartItem);
	}

	/** cartItem을 cart에서 삭제하는 메서드 */
	public void removeCartItem(CartItem cartItem) {
		cartItemDao.removeCartItem(cartItem);
	}

	/** cart에 있는 모든 cartItem을 삭제하는 메서드 */
	public void removeAllCartItem(Cart cart) {
		cartItemDao.removeAllCartItems(cart);
	}

	/** cartId와 productId에 해당하는 cartItem을 반환하는 메서드 */
	public CartItem getCartItemByProductId(int cartId, int productId) {
		return cartItemDao.getCartItemByProductId(cartId, productId);
	}

}
