package edu.kit.isco.KitAlumniApp.server.dbservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessTag;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessUser;

/**
 * This class represents the interface to the database connection
 * @author Alexander Mueller
 *
 */
@WebListener 
public class DbHandlerService {
	
private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("KitAlumniAppPersistenceUnit");;
	
	/**
	 * Executed at server shutdown. Closes all connectivites to the database.
	 */
	public static void close() {
		FACTORY.close();		
	}

	/**
	 * Executed at server start. Initializes the database connectivity and saves the predefined job categories.
	 */
	static {
		EntityManager manager = FACTORY.createEntityManager();
		manager.getTransaction().begin();
		List<DataAccessTag> tags = manager.createQuery("FROM DataAccessTag", DataAccessTag.class).getResultList();
		if (tags.isEmpty()) {
			manager.persist(DataAccessTag.DATA_ADMINISTRATION);
			manager.persist(DataAccessTag.TRAINEE);
			manager.persist(DataAccessTag.CLERK);
			manager.persist(DataAccessTag.GRADUAND);
			manager.persist(DataAccessTag.DOCTORAND);
			manager.persist(DataAccessTag.ENGINEER);
			manager.persist(DataAccessTag.INDUSTRIAL);
			manager.persist(DataAccessTag.SALES_OCCUPATION);
			manager.persist(DataAccessTag.THRESHOLD_WORKER);
			manager.persist(DataAccessTag.PROFESSOR);
			manager.persist(DataAccessTag.TECHNICAL_EMPLOYEE);
			manager.persist(DataAccessTag.STUDENT_RESEARCH_PROJECT);
			manager.persist(DataAccessTag.ADMINISTRATION);
			manager.persist(DataAccessTag.SCIENTIST);
			manager.persist(DataAccessTag.OTHERS);
			manager.getTransaction().commit();
		} else {
			DataAccessTag.DATA_ADMINISTRATION = tags.get(0);
			DataAccessTag.TRAINEE = tags.get(1);
			DataAccessTag.CLERK = tags.get(2);
			DataAccessTag.GRADUAND = tags.get(3);
			DataAccessTag.DOCTORAND = tags.get(4);
			DataAccessTag.ENGINEER = tags.get(5);
			DataAccessTag.INDUSTRIAL = tags.get(6);
			DataAccessTag.SALES_OCCUPATION = tags.get(7);
			DataAccessTag.THRESHOLD_WORKER = tags.get(8);
			DataAccessTag.PROFESSOR = tags.get(9);
			DataAccessTag.TECHNICAL_EMPLOYEE = tags.get(10);
			DataAccessTag.STUDENT_RESEARCH_PROJECT = tags.get(11);
			DataAccessTag.ADMINISTRATION = tags.get(12);
			DataAccessTag.SCIENTIST = tags.get(13);
			DataAccessTag.OTHERS = tags.get(14);
		}
		manager.close();
		
	}
	
	/**
	 * Creates and returns a EntityManager object, which represents a database connection,
	 * which can be used to execute SQL statements. 
	 * A EntityManager object has to be closed after a finished transaction, since it is not threadsafe.
	 * @return a newly created EntityManager object
	 */
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
	/**
	 * Saves a given user into the database.
	 * @param user the user object to save into the database
	 */
	public static void saveUser(DataAccessUser user) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessUser WHERE Id = :Id", DataAccessUser.class);
		q.setParameter("Id", user.getId());
		List<DataAccessUser> list = (List<DataAccessUser>) q.getResultList();
		if (list.isEmpty()) {
			manager.persist(user);
		} else {
			manager.merge(user);
		}
		manager.getTransaction().commit();
		manager.close();
	}
	
	/**
	 * Deletes a user with a given registration id from the database.
	 * @param clientId the registration id of a user
	 */
	public static void deleteUser(String clientId) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("DELETE FROM DataAccessUser WHERE clientId = :clientId");
		q.setParameter("clientId", clientId);
		q.executeUpdate();
		manager.getTransaction().commit();
		manager.close();
	}
	
	/**
	 * Returns a user object by a given registration id. Returns null if no such user exists.
	 * @param clientId the registration id which the user is searched for
	 * @return the user with the given registration id
	 */
	public static DataAccessUser getUser(String clientId) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessUser WHERE clientId = :clientId", DataAccessUser.class);
		q.setParameter("clientId", clientId);
		List<DataAccessUser> list = (List<DataAccessUser>) q.getResultList();
		manager.close();
		if (list.isEmpty())
			return null;
		return list.get(0);
	}
	
	/**
	 * Returns a list of all users saved in the database.
	 * @return the list of all users
	 */
	public static List<DataAccessUser> getAllUsers() {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessUser", DataAccessUser.class);
		List<DataAccessUser> list = (List<DataAccessUser>) q.getResultList();
		manager.close();
		return list;
	}
	
	/**
	 * Returns a list of users which are interested in a given job category. 
	 * @param tag the job category, for which we want all users
	 * @return the list of users
	 */
	public static List<DataAccessUser> getUsersByTag(DataAccessTag tag) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("SELECT u FROM DataAccessUser u JOIN u.tags t WHERE t.name = :name", DataAccessUser.class);
		q.setParameter("name", tag.getName());
		List<DataAccessUser> list = (List<DataAccessUser>) q.getResultList();
		manager.close();
		return list;
	}

	/**
	 * Saves or Updates a given news object into the database.
	 * @param news the news object that is saved or updated
	 */
	public static void saveNews(DataAccessNews news) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(news);
		manager.getTransaction().commit();
		manager.close();
	}
	
	/**
	 * Returns a list of all news with a database id greater than a given id.
	 * @param id the id for which news are searched
	 * @return a list of news
	 */
	public static List<DataAccessNews> getLatestNews(long id) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessNews WHERE Id > :id ORDER BY date", DataAccessNews.class);
		q.setParameter("id", id);
		List<DataAccessNews> list = (List<DataAccessNews>) q.getResultList();
		manager.close();
		return list;
	}
	
	/**
	 * Returns a given amount of news with a database id that is lower than a given id.
	 * @param id the id for which news are searched
	 * @param count the amount of news 
	 * @return a list of news
	 */
	public static List<DataAccessNews> getPreviousNews(long id, long count) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessNews WHERE Id < :id1 AND Id > :id2 ORDER BY date", DataAccessNews.class);
		q.setParameter("id1", id);
		q.setParameter("id2", id - count);
		List<DataAccessNews> list = (List<DataAccessNews>) q.getResultList();
		manager.close();
		return list;
	}
	
	/**
	 * Returns a list of all news saved in the database
	 * @return a list of all news
	 */
	public static List<DataAccessNews> getAllNews() {
		List<DataAccessNews> list = null;
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessNews ORDER BY date", DataAccessNews.class);
		list = (List<DataAccessNews>) q.getResultList();
		manager.close();
		return list;
	}

	/**
	 * Saves or updates a given event object into the database
	 * @param event the event object that is saved or updated
	 */
	public static void saveEvent(DataAccessEvent event) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(event);
		manager.getTransaction().commit();
		manager.close();
	}
	
	/**
	 * Deletes a given event from the database.
	 * @param the event to delete
	 */
	public static void deleteEvent(DataAccessEvent event) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("DELETE FROM DataAccessEvent WHERE id = :id");
		q.setParameter("id", event.getId());
		q.executeUpdate();
		manager.getTransaction().commit();
		manager.close();
	}

	/**
	 * Returns a list of all events with a database id greater than a given id.
	 * @param id the id for which events are searched
	 * @return a list of events
	 */
	public static List<DataAccessEvent> getLatestEvents(long id) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessEvent WHERE Id > :id ORDER BY date DESC", DataAccessEvent.class);
		q.setParameter("id", id);
		List<DataAccessEvent> list = (List<DataAccessEvent>) q.getResultList();
		manager.close();
		return list;
	}

	/**
	 * Returns a given amount of events with a database id that is lower than a given id.
	 * @param id the id for which events are searched
	 * @param count the amount of events 
	 * @return a list of events
	 */
	public static List<DataAccessEvent> getPreviousEvents(long id, long count) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessEvent WHERE Id < :id1 AND Id > :id2 ORDER BY date DESC", DataAccessEvent.class);
		q.setParameter("id1", id);
		q.setParameter("id2", id - count);
		List<DataAccessEvent> list = (List<DataAccessEvent>) q.getResultList();
		manager.close();
		return list;
	}
	
	/**
	 * Returns a list of all events saved in the database
	 * @return a list of all events
	 */
	public static List<DataAccessEvent> getAllEvents() {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessEvent ORDER BY date DESC", DataAccessEvent.class);
		List<DataAccessEvent> list = (List<DataAccessEvent>) q.getResultList();
		manager.close();
		return list;
	}
	
	/**
	 * Saves or updates a given job object into the database
	 * @param event the job object that is saved or updated
	 */
	public static void saveJob(DataAccessJob job) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(job);
		manager.getTransaction().commit();
		manager.close();
	}

	/**
	 * Returns a list of all jobs with a database id greater than a given id.
	 * @param id the id for which jobs are searched
	 * @return a list of jobs
	 */
	public static List<DataAccessJob> getLatestJobs(long id) {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessJob WHERE Id > :id", DataAccessJob.class);
		q.setParameter("id", id);
		List<DataAccessJob> list = (List<DataAccessJob>) q.getResultList();
		manager.close();
		return list;
	}
	
	/**
	 * Returns a given amount of jobs with a database id that is lower than a given id.
	 * @param id the id for which jobs are searched
	 * @param count the amount of jobs 
	 * @return a list of jobs
	 */
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
	
	/**
	 * Returns a list of all jobs saved in the database
	 * @return a list of all jobs
	 */
	public static List<DataAccessJob> getAllJobs() {
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("FROM DataAccessJob", DataAccessJob.class);
		List<DataAccessJob> list = (List<DataAccessJob>) q.getResultList();
		manager.close();
		return list;
	}
	
	/**
	 * Returns a list of jobs which fit to a given job category. 
	 * @param tag the job category, for which we want all jobs
	 * @return the list of jobs
	 */
	public static List<DataAccessJob> getJobsByTag(DataAccessTag tag) {
		/* REQUIRED???? */
		EntityManager manager = DbHandlerService.getEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createQuery("SELECT j FROM DataAccessJob j JOIN j.tags t WHERE t.name = :name", DataAccessJob.class);
		q.setParameter("name", tag.getName());
		List<DataAccessJob> list = (List<DataAccessJob>) q.getResultList();
		manager.close();
		return list;
	}
	
}
