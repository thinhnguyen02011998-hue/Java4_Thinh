package dao;

import java.util.ArrayList;
import java.util.List;

import entity.Video;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import utils.XJPA;

public class VideoDAO implements DAOInterface<Video, String> {

    @Override
    public List<Video> findAll() {
        EntityManager manager = null;
        List<Video> list = new ArrayList<>();

        try {
            manager = XJPA.getEntityManager();
            String jpql = "SELECT v FROM Video v";
            list = manager.createQuery(jpql, Video.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null) manager.close();
        }

        return list;
    }

    public List<Video> findAllActive() {
        EntityManager manager = null;
        List<Video> list = new ArrayList<>();

        try {
            manager = XJPA.getEntityManager();
            String jpql = "SELECT v FROM Video v WHERE v.active = true";
            list = manager.createQuery(jpql, Video.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null) manager.close();
        }

        return list;
    }

    public List<Video> random10Video(String excludeId) {
        EntityManager manager = null;
        List<Video> list = new ArrayList<>();

        try {
            manager = XJPA.getEntityManager();

            // SQL Server dùng NEWID()
            String jpql = "SELECT v FROM Video v WHERE v.id != :id AND v.active = true ORDER BY FUNCTION('NEWID')";
            TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
            query.setParameter("id", excludeId);
            query.setMaxResults(10);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null) manager.close();
        }

        return list;
    }

    @Override
    public Video findById(String id) {
        EntityManager manager = null;
        Video video = null;

        try {
            manager = XJPA.getEntityManager();
            video = manager.find(Video.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null) manager.close();
        }

        return video;
    }

    @Override
    public void create(Video t) {
        EntityManager manager = null;

        try {
            manager = XJPA.getEntityManager();
            manager.getTransaction().begin();

            manager.persist(t);

            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager != null && manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (manager != null) manager.close();
        }
    }

    @Override
    public boolean deleteById(String id) {
        EntityManager manager = null;

        try {
            manager = XJPA.getEntityManager();
            manager.getTransaction().begin();

            Video entity = manager.find(Video.class, id);
            if (entity != null) {
                manager.remove(entity);
            }

            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (manager != null && manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (manager != null) manager.close();
        }

        return false;
    }

    @Override
    public void update(Video t) {
        EntityManager manager = null;

        try {
            manager = XJPA.getEntityManager();
            manager.getTransaction().begin();

            manager.merge(t);

            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager != null && manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (manager != null) manager.close();
        }
    }

    public List<Video> findVideosLikedByUser(String userId) {
        EntityManager manager = null;
        List<Video> list = new ArrayList<>();

        try {
            manager = XJPA.getEntityManager();

            String jpql = "SELECT f.video FROM Favorite f WHERE f.user.id = :userId";
            TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
            query.setParameter("userId", userId);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null) manager.close();
        }

        return list;
    }
}