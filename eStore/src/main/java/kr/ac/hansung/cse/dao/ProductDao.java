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

	// id�� �ش��ϴ� product�� DB���� ��ȸ�Ѵ�.
	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, id);

		// Product product = sessionFactory.getCurrentSession().get(Product.class, id);

		return product;
	}

	// DB���� ��� record�� ��ȸ�ؼ� ����� List�� ���·� �Ѱܹ޴´�.
	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product"); // HQL�� �̿�. table�̸��� �ƴ϶� class�̸�
		List<Product> productList = query.list();

		return productList;
	}

	// product�� �ʵ尪�� �ϳ��� �����ͼ� DB�� �������ش�.
	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}

	// id�� �ش��ϴ� product�� DB���� �����Ѵ�.
	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
	}

	// product�� �����Ͽ� ������ ������ DB�� �ݿ��Ѵ�.
	public void editProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}
}
