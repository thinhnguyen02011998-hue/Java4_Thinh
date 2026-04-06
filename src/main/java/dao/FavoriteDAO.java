package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.ReportFavoriteUser;
import dto.ReportFavorites;
import entity.Favorite;
import entity.Video;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.XJPA;

public class FavoriteDAO implements DAOInterface<Favorite, Integer> {

	@Override
	public List<Favorite> findAll() {
		List<Favorite> list = new ArrayList();
		SessionFactory factory = null;
		EntityManager manager = null;
		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();
				Query query = manager.createQuery("from Favorite");
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
	public Favorite findById(Integer id) {
		Favorite favorite = null;
		SessionFactory factory = null;
		EntityManager manager = null;
		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();
				Query query = manager.createQuery("from Favorite u where u.id = :id");
				query.setParameter("id", id);
				favorite = (Favorite) query.getSingleResult();
				manager.getTransaction().commit();
				manager.close();
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return favorite;
	}

	@Override
	public void create(Favorite t) {
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
		Favorite entity = this.findById(id);
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
	public void update(Favorite t) {
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

	public List<Video> findTop10MostFavoritedVideos() {
		List<Video> videos = new ArrayList<>();
		SessionFactory factory = null;
		EntityManager manager = null;

		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();

				String jpql = "SELECT f.video FROM Favorite f GROUP BY f.video ORDER BY COUNT(f) DESC";
				TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
				query.setMaxResults(10); // Lấy tối đa 10 kết quả

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

	public List<Video> findVideosWithNoFavorites() {
		List<Video> videos = new ArrayList<>();
		SessionFactory factory = null;
		EntityManager manager = null;

		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();

				String jpql = "SELECT v FROM Video v LEFT JOIN v.favorites f WHERE f.id IS NULL";
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

	public Favorite findByUserIdAndVideoId(String userId, String videoId) {
		Favorite favorite = null;
		SessionFactory factory = null;
		EntityManager manager = null;

		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();

				String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId";
				TypedQuery<Favorite> query = manager.createQuery(jpql, Favorite.class);
				query.setParameter("userId", userId);
				query.setParameter("videoId", videoId);

				List<Favorite> result = query.getResultList();
				favorite = result.isEmpty() ? null : result.get(0);

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

		return favorite;
	}

	public List<Favorite> findFavoritesByUserId(String userId) {
		List<Favorite> favorites = new ArrayList<>();
		SessionFactory factory = null;
		EntityManager manager = null;

		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();

				String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :userId";
				TypedQuery<Favorite> query = manager.createQuery(jpql, Favorite.class);
				query.setParameter("userId", userId);

				favorites = query.getResultList();

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

		return favorites;
	}

	public List<ReportFavorites> generateFavoriteReport() {
		List<ReportFavorites> report = new ArrayList<>();
		SessionFactory factory = null;
		EntityManager manager = null;

		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();

				String jpql = "SELECT new dto.ReportFavorites(f.video.title, COUNT(f), MAX(f.shareDate), MIN(f.shareDate)) FROM Favorite f GROUP BY f.video.title";
				TypedQuery<ReportFavorites> query = manager.createQuery(jpql, ReportFavorites.class);

				report = query.getResultList();

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

		return report;
	}

	public List<ReportFavoriteUser> findUsersByVideoId(String videoId) {
		List<ReportFavoriteUser> users = new ArrayList<>();
		SessionFactory factory = null;
		EntityManager manager = null;

		try {
			factory = XJPA.getSessionFactory();
			if (factory != null) {
				manager = factory.createEntityManager();
				manager.getTransaction().begin();

				String jpql = "SELECT new dto.ReportFavoriteUser(u.id, u.email, u.fullname, f.shareDate) FROM Favorite f JOIN f.user u WHERE f.video.id = :videoId ORDER BY f.shareDate DESC";
				TypedQuery<ReportFavoriteUser> query = manager.createQuery(jpql, ReportFavoriteUser.class);
				query.setParameter("videoId", videoId);

				users = query.getResultList();

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

		return users;
	}

}
