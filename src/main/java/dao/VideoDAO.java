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
    
 // Thêm vào VideoDAO.java
    public List<Video> findVideosLikedByUserWithStats(String userId) {
        List<Video> list = new ArrayList<>();
        Session session = XJPA.getSessionFactory().openSession();
        try {
            // Query lấy video từ bảng Favorite và đếm stats tương ứng của video đó
            String hql = "SELECT f.video, " +
                         "(SELECT COUNT(f2) FROM Favorite f2 WHERE f2.video.id = f.video.id), " +
                         "(SELECT COUNT(s) FROM Share s WHERE s.video.id = f.video.id) " +
                         "FROM Favorite f WHERE f.user.id = :userId";
            
            List<Object[]> results = session.createQuery(hql)
                                         .setParameter("userId", userId)
                                         .list();
            for (Object[] row : results) {
                Video v = (Video) row[0];
                v.setFavoriteCount((Long) row[1]);
                v.setShareCount((Long) row[2]);
                list.add(v);
            }
        } finally {
            session.close();
        }
        return list;
    }
 // Chép đoạn này vào trong class VideoDAO
    public Video findByIdWithStats(String id) {
        Session session = XJPA.getSessionFactory().openSession();
        try {
            String hql = "SELECT v, " +
                         "(SELECT COUNT(f) FROM Favorite f WHERE f.video.id = v.id), " +
                         "(SELECT COUNT(s) FROM Share s WHERE s.video.id = v.id) " +
                         "FROM Video v WHERE v.id = :id";
            
            Object resultObj = session.createQuery(hql).setParameter("id", id).uniqueResult();
            
            if (resultObj != null) {
                Object[] row = (Object[]) resultObj;
                Video v = (Video) row[0];
                v.setFavoriteCount((Long) row[1]);
                v.setShareCount((Long) row[2]);
                return v;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    
 // Thêm hàm này vào trong class VideoDAO
    public List<Video> findAllWithStats() {
        List<Video> list = new java.util.ArrayList<>();
        Session session = XJPA.getSessionFactory().openSession();
        try {
            // HQL lấy tất cả video và đếm số Like, số Share của từng video đó
            String hql = "SELECT v, " +
                         "(SELECT COUNT(f) FROM Favorite f WHERE f.video.id = v.id), " +
                         "(SELECT COUNT(s) FROM Share s WHERE s.video.id = v.id) " +
                         "FROM Video v";
            
            List<Object[]> results = session.createQuery(hql).list();
            
            for (Object[] row : results) {
                Video v = (Video) row[0];
                v.setFavoriteCount((Long) row[1]); // Gán số lượt Like
                v.setShareCount((Long) row[2]);    // Gán số lượt Share
                list.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
   
}















