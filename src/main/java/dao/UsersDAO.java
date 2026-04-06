package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Users;
import utils.XJPA;

public class UsersDAO implements DAOInterface<Users, String> {

    @Override
    public List<Users> findAll() {
        List<Users> list = new ArrayList<>();
        Session session = XJPA.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            list = session.createQuery("from Users", Users.class).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public Users findById(String id) {
        Session session = XJPA.getSessionFactory().openSession();
        Users user = null;

        try {
            user = session.get(Users.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public void create(Users t) {
        Session session = XJPA.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(t);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteById(String id) {
        Session session = XJPA.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Users user = session.get(Users.class, id);
            if (user != null) {
                session.delete(user);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }

    @Override
    public void update(Users t) {
        Session session = XJPA.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.update(t);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // 🔥 LOGIN / SEARCH USER
    public Users findByIdOrEmail(String idOrEmail) {
        Session session = XJPA.getSessionFactory().openSession();
        Users user = null;

        try {
            String hql = "FROM Users u WHERE u.id = :value OR u.email = :value";

            user = session.createQuery(hql, Users.class)
                    .setParameter("value", idOrEmail)
                    .uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }
}



















//package dao;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import entity.Users;
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.TypedQuery;
//
//import utils.XJPA;
//
//public class UsersDAO implements DAOInterface<Users, String> {
//
//    @Override
//    public List<Users> findAll() {
//        EntityManager manager = null;
//        List<Users> list = new ArrayList<>();
//
//        try {
//            manager = XJPA.getEntityManager();
//            String jpql = "SELECT u FROM Users u";
//            list = manager.createQuery(jpql, Users.class).getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//
//        return list;
//    }
//
//    @Override
//    public Users findById(String id) {
//        EntityManager manager = null;
//        Users user = null;
//
//        try {
//            manager = XJPA.getEntityManager();
//            user = manager.find(Users.class, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//
//        return user;
//    }
//
//    @Override
//    public void create(Users t) {
//        EntityManager manager = null;
//
//        try {
//            manager = XJPA.getEntityManager();
//            manager.getTransaction().begin();
//
//            manager.persist(t);
//
//            manager.getTransaction().commit();
//        } catch (Exception e) {
//            if (manager != null && manager.getTransaction().isActive()) {
//                manager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//    }
//
//    @Override
//    public boolean deleteById(String id) {
//        EntityManager manager = null;
//
//        try {
//            manager = XJPA.getEntityManager();
//            manager.getTransaction().begin();
//
//            Users entity = manager.find(Users.class, id);
//            if (entity != null) {
//                manager.remove(entity);
//            }
//
//            manager.getTransaction().commit();
//            return true;
//
//        } catch (Exception e) {
//            if (manager != null && manager.getTransaction().isActive()) {
//                manager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//
//        return false;
//    }
//
//    @Override
//    public void update(Users t) {
//        EntityManager manager = null;
//
//        try {
//            manager = XJPA.getEntityManager();
//            manager.getTransaction().begin();
//
//            manager.merge(t);
//
//            manager.getTransaction().commit();
//        } catch (Exception e) {
//            if (manager != null && manager.getTransaction().isActive()) {
//                manager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//    }
//
//    // LOGIN / SEARCH USER
//    public Users findByIdOrEmail(String idOrEmail) {
//        EntityManager manager = null;
//        Users user = null;
//
//        try {
//            manager = XJPA.getEntityManager();
//
//            String jpql = "SELECT u FROM Users u WHERE u.id = :id OR u.email = :email";
//            TypedQuery<Users> query = manager.createQuery(jpql, Users.class);
//            query.setParameter("id", idOrEmail);
//            query.setParameter("email", idOrEmail);
//
//            user = query.getSingleResult();
//
//        } catch (NoResultException e) {
//            user = null;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//
//        return user;
//    }
//}
//
//
//
//
//
//
//
//
//
//
////package dao;
////
////import java.util.ArrayList;
////import java.util.List;
////
////import entity.Users;
////import javax.persistence.EntityManager;
////import javax.persistence.NoResultException;
////import javax.persistence.TypedQuery;
////
////import utils.XJPA;
////
////public class UsersDAO implements DAOInterface<Users, String> {
////
////    @Override
////    public List<Users> findAll() {
////        EntityManager manager = null;
////        List<Users> list = new ArrayList<>();
////
////        try {
////            manager = XJPA.getEntityManager();
////            String jpql = "SELECT u FROM Users u";
////            list = manager.createQuery(jpql, Users.class).getResultList();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////
////        return list;
////    }
////
////    @Override
////    public Users findById(String id) {
////        EntityManager manager = null;
////        Users user = null;
////
////        try {
////            manager = XJPA.getEntityManager();
////            user = manager.find(Users.class, id);
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////
////        return user;
////    }
////
////    @Override
////    public void create(Users t) {
////        EntityManager manager = null;
////
////        try {
////            manager = XJPA.getEntityManager();
////            manager.getTransaction().begin();
////
////            manager.persist(t);
////
////            manager.getTransaction().commit();
////        } catch (Exception e) {
////            if (manager != null && manager.getTransaction().isActive()) {
////                manager.getTransaction().rollback();
////            }
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////    }
////
////    @Override
////    public boolean deleteById(String id) {
////        EntityManager manager = null;
////
////        try {
////            manager = XJPA.getEntityManager();
////            manager.getTransaction().begin();
////
////            Users entity = manager.find(Users.class, id);
////            if (entity != null) {
////                manager.remove(entity);
////            }
////
////            manager.getTransaction().commit();
////            return true;
////
////        } catch (Exception e) {
////            if (manager != null && manager.getTransaction().isActive()) {
////                manager.getTransaction().rollback();
////            }
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////
////        return false;
////    }
////
////    @Override
////    public void update(Users t) {
////        EntityManager manager = null;
////
////        try {
////            manager = XJPA.getEntityManager();
////            manager.getTransaction().begin();
////
////            manager.merge(t);
////
////            manager.getTransaction().commit();
////        } catch (Exception e) {
////            if (manager != null && manager.getTransaction().isActive()) {
////                manager.getTransaction().rollback();
////            }
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////    }
////
////    // LOGIN / SEARCH USER
////    public Users findByIdOrEmail(String idOrEmail) {
////        EntityManager manager = null;
////        Users user = null;
////
////        try {
////            manager = XJPA.getEntityManager();
////
////            String jpql = "SELECT u FROM Users u WHERE u.id = :id OR u.email = :email";
////            TypedQuery<Users> query = manager.createQuery(jpql, Users.class);
////            query.setParameter("id", idOrEmail);
////            query.setParameter("email", idOrEmail);
////
////            user = query.getSingleResult();
////
////        } catch (NoResultException e) {
////            user = null;
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////
////        return user;
////    }
////}