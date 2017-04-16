package kr.ac.hansung.cse.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Product;

@Repository
@Transactional
public class ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	// id에 해당하는 product를 DB에서 조회한다.
	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, id);

		// Product product = sessionFactory.getCurrentSession().get(Product.class, id);

		return product;
	}

	// DB에서 모든 record를 조회해서 결과를 List의 형태로 넘겨받는다.
	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product"); // HQL문 이용. table이름이 아니라 class이름
		List<Product> productList = query.list();

		return productList;
	}

	// product의 필드값을 하나씩 가져와서 DB에 저장해준다.
	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}

	// id에 해당하는 product를 DB에서 제거한다.
	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
	}

	// product를 선택하여 수정한 내용을 DB에 반영한다.
	public void editProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}
}
