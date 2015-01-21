package edu.kit.isco.KitAlumniApp.server.dbservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
}
