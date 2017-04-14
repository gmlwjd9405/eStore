package testHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestMain {
	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		// configure(): ���� ����(hibernate.cfg.xml)�� �������� �����Ѵ�.
		// buildSessionFactory(): SessionFactory�� �ϳ� ����
		sessionFactory = new Configuration().configure().buildSessionFactory();

		// ���� ���� ������ ȣ��
		Product product = new Product();
		product.setName("��Ʈ��");
		product.setPrice(2000);
		product.setDescription("Awesome Notebook");

		// Session ����
		Session session = sessionFactory.openSession();
		// DB�� ��� save�� �ϴ� ��� Transaction�� �ʿ�
		Transaction tx = session.beginTransaction();

		session.save(product);

		tx.commit();
		session.close();
	}
}
