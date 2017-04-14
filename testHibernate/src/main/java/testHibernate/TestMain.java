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

		// 인자 없는 생성자 호출
		Product product = new Product();
		product.setName("노트북");
		product.setPrice(2000);
		product.setDescription("Awesome Notebook");

		// Session 생성
		Session session = sessionFactory.openSession();
		// DB의 경우 save를 하는 경우 Transaction이 필요
		Transaction tx = session.beginTransaction();

		session.save(product);

		tx.commit();
		session.close();
	}
}
