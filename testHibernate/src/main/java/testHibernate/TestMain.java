package testHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestMain {
	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		// configure(): 설정 파일(hibernate.cfg.xml)을 바탕으로 설정한다.
		// buildSessionFactory(): SessionFactory를 하나 생성
		sessionFactory = new Configuration().configure().buildSessionFactory();

		Category category1 = new Category();
		category1.setName("Computer");

		Category category2 = new Category();
		category2.setName("Car");

		// 인자 없는 생성자 호출
		Product product1 = new Product();
		product1.setName("노트북1");
		product1.setPrice(2000);
		product1.setDescription("Awesome Notebook");
		product1.setCategory(category1); // 객체에 대한 reference category1. 나중에 FK로 바뀐다.

		Product product2 = new Product();
		product2.setName("노트북2");
		product2.setPrice(300);
		product2.setDescription("Powerful Notebook");
		product2.setCategory(category1); // 객체에 대한 reference category1. 나중에 FK로 바뀐다.

		Product product3 = new Product();
		product3.setName("소나타");
		product3.setPrice(10000);
		product3.setDescription("대중적인 자동차");
		product3.setCategory(category2); // 객체에 대한 reference category1. 나중에 FK로 바뀐다.

		// Session 생성
		Session session = sessionFactory.openSession();
		// DB의 경우 save를 하는 경우 Transaction이 필요
		Transaction tx = session.beginTransaction();

		session.save(product1); 
		session.save(product2);
		session.save(product3);

		tx.commit();
		session.close();
	}
}
