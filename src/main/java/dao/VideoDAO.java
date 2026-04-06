package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Video;
import utils.XJPA;

public class VideoDAO implements DAOInterface<Video, String> {

    @Override
    public List<Video> findAll() {
        List<Video> list = new ArrayList<>();
        Session session = XJPA.getSessionFactory().openSession();

        try {
            list = session.createQuery("FROM Video", Video.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    public List<Video> findAllActive() {
        List<Video> list = new ArrayList<>();
        Session session = XJPA.getSessionFactory().openSession();

        try {
            list = session.createQuery("FROM Video v WHERE v.active = true", Video.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    public List<Video> random10Video(String excludeId) {
        List<Video> list = new ArrayList<>();
        Session session = XJPA.getSessionFactory().openSession();

        try {
            String hql = "FROM Video v WHERE v.id != :id AND v.active = true ORDER BY rand()";

            list = session.createQuery(hql, Video.class)
                    .setParameter("id", excludeId)
                    .setMaxResults(10)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public Video findById(String id) {
        Session session = XJPA.getSessionFactory().openSession();
        Video video = null;

        try {
            video = session.get(Video.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return video;
    }

    @Override
    public void create(Video t) {
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
            Video video = session.get(Video.class, id);
            if (video != null) {
                session.delete(video);
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
    public void update(Video t) {
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

    public List<Video> findVideosLikedByUser(String userId) {
        List<Video> list = new ArrayList<>();
        Session session = XJPA.getSessionFactory().openSession();

        try {
            String hql = "SELECT f.video FROM Favorite f WHERE f.user.id = :userId";

            list = session.createQuery(hql, Video.class)
                    .setParameter("userId", userId)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }
}



















//package dao;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import entity.Video;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//
//import utils.XJPA;
//
//public class VideoDAO implements DAOInterface<Video, String> {
//
//    @Override
//    public List<Video> findAll() {
//        EntityManager manager = null;
//        List<Video> list = new ArrayList<>();
//
//        try {
//            manager = XJPA.getEntityManager();
//            String jpql = "SELECT v FROM Video v";
//            list = manager.createQuery(jpql, Video.class).getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//
//        return list;
//    }
//
//    public List<Video> findAllActive() {
//        EntityManager manager = null;
//        List<Video> list = new ArrayList<>();
//
//        try {
//            manager = XJPA.getEntityManager();
//            String jpql = "SELECT v FROM Video v WHERE v.active = true";
//            list = manager.createQuery(jpql, Video.class).getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//
//        return list;
//    }
//
//    public List<Video> random10Video(String excludeId) {
//        EntityManager manager = null;
//        List<Video> list = new ArrayList<>();
//
//        try {
//            manager = XJPA.getEntityManager();
//
//            // SQL Server dùng NEWID()
//            String jpql = "SELECT v FROM Video v WHERE v.id != :id AND v.active = true ORDER BY FUNCTION('NEWID')";
//            TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
//            query.setParameter("id", excludeId);
//            query.setMaxResults(10);
//
//            list = query.getResultList();
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
//    public Video findById(String id) {
//        EntityManager manager = null;
//        Video video = null;
//
//        try {
//            manager = XJPA.getEntityManager();
//            video = manager.find(Video.class, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//
//        return video;
//    }
//
//    @Override
//    public void create(Video t) {
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
//            Video entity = manager.find(Video.class, id);
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
//    public void update(Video t) {
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
//    public List<Video> findVideosLikedByUser(String userId) {
//        EntityManager manager = null;
//        List<Video> list = new ArrayList<>();
//
//        try {
//            manager = XJPA.getEntityManager();
//
//            String jpql = "SELECT f.video FROM Favorite f WHERE f.user.id = :userId";
//            TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
//            query.setParameter("userId", userId);
//
//            list = query.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (manager != null) manager.close();
//        }
//
//        return list;
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
////import entity.Video;
////import javax.persistence.EntityManager;
////import javax.persistence.TypedQuery;
////
////import utils.XJPA;
////
////public class VideoDAO implements DAOInterface<Video, String> {
////
////    @Override
////    public List<Video> findAll() {
////        EntityManager manager = null;
////        List<Video> list = new ArrayList<>();
////
////        try {
////            manager = XJPA.getEntityManager();
////            String jpql = "SELECT v FROM Video v";
////            list = manager.createQuery(jpql, Video.class).getResultList();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////
////        return list;
////    }
////
////    public List<Video> findAllActive() {
////        EntityManager manager = null;
////        List<Video> list = new ArrayList<>();
////
////        try {
////            manager = XJPA.getEntityManager();
////            String jpql = "SELECT v FROM Video v WHERE v.active = true";
////            list = manager.createQuery(jpql, Video.class).getResultList();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////
////        return list;
////    }
////
////    public List<Video> random10Video(String excludeId) {
////        EntityManager manager = null;
////        List<Video> list = new ArrayList<>();
////
////        try {
////            manager = XJPA.getEntityManager();
////
////            // SQL Server dùng NEWID()
////            String jpql = "SELECT v FROM Video v WHERE v.id != :id AND v.active = true ORDER BY FUNCTION('NEWID')";
////            TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
////            query.setParameter("id", excludeId);
////            query.setMaxResults(10);
////
////            list = query.getResultList();
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
////    public Video findById(String id) {
////        EntityManager manager = null;
////        Video video = null;
////
////        try {
////            manager = XJPA.getEntityManager();
////            video = manager.find(Video.class, id);
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////
////        return video;
////    }
////
////    @Override
////    public void create(Video t) {
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
////            Video entity = manager.find(Video.class, id);
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
////    public void update(Video t) {
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
////    public List<Video> findVideosLikedByUser(String userId) {
////        EntityManager manager = null;
////        List<Video> list = new ArrayList<>();
////
////        try {
////            manager = XJPA.getEntityManager();
////
////            String jpql = "SELECT f.video FROM Favorite f WHERE f.user.id = :userId";
////            TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
////            query.setParameter("userId", userId);
////
////            list = query.getResultList();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (manager != null) manager.close();
////        }
////
////        return list;
////    }
////}