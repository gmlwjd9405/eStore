package kr.ac.hansung.cse.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;

@Repository
@Transactional
public class CartItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	/** cartItem을 cart에 담는, 추가하는 메서드 */
	public void addCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem); // 기존에 cartItem이 있으면 update, 없으면 save
		session.flush();
	}

	/** cartItem을 cart에서 삭제하는 메서드 */
	public void removeCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cartItem);
		session.flush();
	}

	/** cart에 있는 모든 cartItem을 삭제하는 메서드 */
	public void removeAllCartItems(Cart cart) {
		List<CartItem> cartItems = cart.getCartItems();

		for (CartItem item : cartItems) {
			removeCartItem(item);
		}
	}

	/** cartId와 productId에 해당하는 cartItem을 반환하는 메서드 */
	public CartItem getCartItemByProductId(int cartId, int productId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CartItem where cart.cartId = :cid and product.id = :pid");

		query.setInteger("cid", cartId);
		query.setInteger("pid", productId);

		return (CartItem) query.uniqueResult();
	}

}
