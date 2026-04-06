package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.ReportShareFriend;
import entity.Share;
import entity.Video;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.XJPA;

public class ShareDAO implements DAOInterface<Share, Integer> {

	@Override
	public List<Share> findAll() {
		List<Share> list = new ArrayList();
		SessionFactory factory = null;
		EntityManager manager = null;
		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();
				Query query = manager.createQuery("from Share");
				list = query.getResultList();
				manager.getTransaction().commit();
				manager.close();
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Share findById(Integer id) {
		Share share = null;
		SessionFactory factory = null;
		EntityManager manager = null;
		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();
				Query query = manager.createQuery("from Share u where u.id = :id");
				query.setParameter("id", id);
				share = (Share) query.getSingleResult();
				manager.getTransaction().commit();
				manager.close();
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return share;
	}

	@Override
	public void create(Share t) {
		SessionFactory factory = null;
		Session manager = null;
		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.openSession();
				manager.getTransaction().begin();
				manager.persist(t);
				manager.getTransaction().commit();
				manager.close();
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteById(Integer id) {
		Share entity = this.findById(id);
		SessionFactory factory = null;
		EntityManager manager = null;
		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();
				manager.remove(entity);
				manager.getTransaction().commit();
				manager.close();
				return true;
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return false;
	}

	@Override
	public void update(Share t) {
		SessionFactory factory = null;
		EntityManager manager = null;
		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();
				manager.merge(t);
				manager.getTransaction().commit();
				manager.close();
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public List<Video> findVideosSharedIn2024() {
		List<Video> videos = new ArrayList<>();
		SessionFactory factory = null;
		EntityManager manager = null;

		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();

				String jpql = "SELECT s.video FROM Share s WHERE YEAR(s.shareDate) = 2024 ORDER BY s.shareDate";
				TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
				videos = query.getResultList();

				manager.getTransaction().commit();
			}
		} catch (Exception e) {
			if (manager != null && manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (manager != null) {
				manager.close();
			}
		}

		return videos;
	}

	public List<Object[]> getShareForVideos() {
		List<Object[]> results = new ArrayList<>();
		SessionFactory factory = null;
		EntityManager manager = null;

		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();

				String jpql = "SELECT v.title, COUNT(s.id) AS share_count, MIN(s.shareDate) AS min_share_date, MAX(s.shareDate) AS max_share_date "
						+ "FROM Share s JOIN s.video v " + "GROUP BY v.title ORDER BY v.title";
				TypedQuery<Object[]> query = manager.createQuery(jpql, Object[].class);
				results = query.getResultList();

				manager.getTransaction().commit();
			}
		} catch (Exception e) {
			if (manager != null && manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (manager != null) {
				manager.close();
			}
		}

		return results;
	}

	public List<ReportShareFriend> findReportShareFriendsByVideoId(String videoId) {
		List<ReportShareFriend> reports = new ArrayList<>();
		SessionFactory factory = null;
		EntityManager manager = null;

		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();

				String jpql = "SELECT new dto.ReportShareFriend(u.fullname, u.email, s.emails, "
						+ "(SELECT MAX(f.shareDate) FROM Favorite f WHERE f.video.id = :videoId AND f.user.id = u.id), "
						+ "s.shareDate) " + "FROM Share s JOIN s.user u WHERE s.video.id = :videoId";
				TypedQuery<ReportShareFriend> query = manager.createQuery(jpql, ReportShareFriend.class);
				query.setParameter("videoId", videoId);

				reports = query.getResultList();

				manager.getTransaction().commit();
			}
		} catch (Exception e) {
			if (manager != null && manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (manager != null) {
				manager.close();
			}
		}

		return reports;
	}

}
