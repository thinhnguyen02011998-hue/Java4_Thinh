package utils.jpa;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class XJPAs {
	public static SessionFactory SESSION_FACTORY = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

	public static void shutdown() {
		if (SESSION_FACTORY != null && SESSION_FACTORY.isClosed()) {
			SESSION_FACTORY.close();
		}
		SESSION_FACTORY = null;
	}
}
