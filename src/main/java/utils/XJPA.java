//package utils;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class XJPA {
//
//	static EntityManagerFactory factory;
//
//    static {
//        factory = Persistence.createEntityManagerFactory("PolyOES");
//    }
//
//    public static EntityManager getEntityManager() {
//        return factory.createEntityManager();
//    }
//    
//}









package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class XJPA {
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
}