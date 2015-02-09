package edu.kit.isco.KitAlumniApp.server.dbservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.kit.isco.KitAlumniApp.server.dataobject.*;

@WebListener 
public class DbHandlerService implements ServletContextListener{
	
private static EntityManagerFactory FACTORY;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		FACTORY.close();		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		FACTORY = Persistence.createEntityManagerFactory("KitAlumniAppPersistenceUnit");
		
		EntityManager manager = FACTORY.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(DataAccessTag.INFORMATICS);
		manager.persist(DataAccessTag.CHEMISTRY);
		manager.persist(DataAccessTag.BIOLOGY);
		manager.persist(DataAccessTag.MATHEMATICS);
		manager.persist(DataAccessTag.ELECTRICAL_ENGINEERING);
		manager.persist(DataAccessTag.ENGINEERING);
		manager.persist(DataAccessTag.PHYSICS);
		manager.persist(DataAccessTag.PROCESS_ENGINEERING);
		manager.persist(DataAccessTag.MEDICINE);
		manager.persist(DataAccessTag.ECONOMICS);
		manager.persist(DataAccessTag.LAW);
		manager.persist(DataAccessTag.MECHANICAL_ENGINEERING);
		manager.getTransaction().commit();
		manager.close();
		
	}
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
	public static void saveUser(DataAccessUser user) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(user);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public static void deleteUser(String clientId) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("DELETE FROM DataAccessUser WHERE clientId = :clientId");
		q.setParameter("clientId", clientId);
		q.executeUpdate();
		manager.getTransaction().commit();
		manager.close();
	}
	
	public static DataAccessUser getUser(String clientId) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessUser WHERE clientId = :clientId", DataAccessUser.class);
		q.setParameter("clientId", clientId);
		List<DataAccessUser> list = (List<DataAccessUser>) q.getResultList();
		manager.close();
		return list.get(0);
	}
	
	public static List<DataAccessUser> getAllUsers() {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessUser", DataAccessUser.class);
		List<DataAccessUser> list = (List<DataAccessUser>) q.getResultList();
		manager.close();
		return list;
	}
	
	public static List<DataAccessUser> getUsersByTag(DataAccessTag tag) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("SELECT u FROM DataAccessUser u JOIN u.tags t WHERE t.name = :name", DataAccessUser.class);
		q.setParameter("name", tag.getName());
		List<DataAccessUser> list = (List<DataAccessUser>) q.getResultList();
		manager.close();
		return list;
	}

	public static void saveNews(DataAccessNews news) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(news);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public static void saveNews(List<DataAccessNews> news) {
		for (DataAccessNews n : news) {
			DbHandlerService.saveNews(n);
		}
	}
	
	public static List<DataAccessNews> getLatestNews(long id) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessNews WHERE Id > :id", DataAccessNews.class);
		q.setParameter("id", id);
		List<DataAccessNews> list = (List<DataAccessNews>) q.getResultList();
		manager.close();
		return list;
	}
	
	public static List<DataAccessNews> getPreviousNews(long id, long count) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessNews WHERE Id < :id1 AND Id > :id2", DataAccessNews.class);
		q.setParameter("id1", id);
		q.setParameter("id2", id - count);
		List<DataAccessNews> list = (List<DataAccessNews>) q.getResultList();
		manager.close();
		return list;
	}
	
	public static List<DataAccessNews> getAllNews() {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessNews", DataAccessNews.class);
		List<DataAccessNews> list = (List<DataAccessNews>) q.getResultList();
		manager.close();
		return list;
	}

	public static void saveEvent(DataAccessEvent event) {
		event.toString();
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(event);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public static void saveEvents(List<DataAccessEvent> events) {
		for (DataAccessEvent e : events) {
			DbHandlerService.saveEvent(e);
		}
	}

	public static List<DataAccessEvent> getLatestEvents(long id) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessEvent WHERE Id > :id", DataAccessEvent.class);
		q.setParameter("id", id);
		List<DataAccessEvent> list = (List<DataAccessEvent>) q.getResultList();
		manager.close();
		return list;
	}

	public static List<DataAccessEvent> getPreviousEvents(long id, long count) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessEvent WHERE Id < :id1 AND Id > :id2", DataAccessEvent.class);
		q.setParameter("id1", id);
		q.setParameter("id2", id - count);
		List<DataAccessEvent> list = (List<DataAccessEvent>) q.getResultList();
		manager.close();
		return list;
	}
	
	public static List<DataAccessEvent> getAllEvents() {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessEvent", DataAccessEvent.class);
		List<DataAccessEvent> list = (List<DataAccessEvent>) q.getResultList();
		manager.close();
		return list;
	}
	
	public static void saveJob(DataAccessJob job) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(job);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public static void saveJobs(List<DataAccessJob> jobs) {
		for (DataAccessJob j : jobs) {
			DbHandlerService.saveJob(j);
		}
	}
	
	public static List<DataAccessJob> getLatestJobs(long id) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessJob WHERE Id > :id", DataAccessJob.class);
		q.setParameter("id", id);
		List<DataAccessJob> list = (List<DataAccessJob>) q.getResultList();
		manager.close();
		return list;
	}
	
	public static List<DataAccessJob> getPreviousJobs(long id, long count) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessJob WHERE Id < :id1 AND Id > :id2", DataAccessJob.class);
		q.setParameter("id1", id);
		q.setParameter("id2", id - count);
		List<DataAccessJob> list = (List<DataAccessJob>) q.getResultList();
		manager.close();
		return list;
	}
	
	public static List<DataAccessJob> getAllJobs() {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessJob", DataAccessJob.class);
		List<DataAccessJob> list = (List<DataAccessJob>) q.getResultList();
		manager.close();
		return list;
	}
	
	public static List<DataAccessJob> getJobsByTag(DataAccessTag tag) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("SELECT j FROM DataAccessJob j JOIN j.tags t WHERE t.name = :name", DataAccessJob.class);
		q.setParameter("name", tag.getName());
		List<DataAccessJob> list = (List<DataAccessJob>) q.getResultList();
		manager.close();
		return list;
	}
}
