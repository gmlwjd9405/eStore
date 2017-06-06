package kr.ac.hansung.cse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Cart;

@Repository
@Transactional
public class CartDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/** cartId에 해당하는 cart에 대한 정보를 실제 DB(Hibernate를 이용)에서 가져오는 메서드 */
	public Cart getCartById(int cartId) {
		Session session = sessionFactory.getCurrentSession();
		return (Cart) session.get(Cart.class, cartId);
	}

}
