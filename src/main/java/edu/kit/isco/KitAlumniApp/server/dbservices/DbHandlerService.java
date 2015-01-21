package edu.kit.isco.KitAlumniApp.server.dbservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;

public class DbHandlerService {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("KitAlumniAppPersistenceUnit");
	
	public static List<DataAccessNews> getAllNews() {
		EntityManager em = factory.createEntityManager();
		TypedQuery<DataAccessNews> namedQuery = em.createNamedQuery("DataAccessNews.getAll", DataAccessNews.class);
		List<DataAccessNews> result = namedQuery.getResultList();
		em.close();
		return result;
	}
	
	public static void saveNews(DataAccessNews news) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(news);
		tx.commit();
		em.close();
	}
	
	public static void saveNews(List<DataAccessNews> news) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		for (DataAccessNews n : news) {
			em.merge(n);
		}
		tx.commit();
		em.close();
	}
	
	public static DataAccessNews getNews(long id) {
		EntityManager em = factory.createEntityManager();
		DataAccessNews result = em.find(DataAccessNews.class, id);
		em.close();
		return result;
	}
}
