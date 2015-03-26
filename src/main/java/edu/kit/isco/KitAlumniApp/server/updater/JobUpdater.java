package edu.kit.isco.KitAlumniApp.server.updater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessUser;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;
import edu.kit.isco.KitAlumniApp.server.gcm.ApiKeyInitializer;
import edu.kit.isco.KitAlumniApp.server.gcm.Constants;
import edu.kit.isco.KitAlumniApp.server.gcm.Message;
import edu.kit.isco.KitAlumniApp.server.gcm.MulticastResult;
import edu.kit.isco.KitAlumniApp.server.gcm.Result;
import edu.kit.isco.KitAlumniApp.server.gcm.Sender;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;

/**
 * An Updater class that periodically parses job data from a website, checks for new jobs, saves them in the database
 * and sends out notification to user devices.
 * @author Alexander Mueller
 *
 */
public class JobUpdater extends AbstractUpdater {
	
	/**
	 * A Sender object with which the notifications are sent
	 */
	private Sender sender;
	
	/**
	 * Creates a new JobUpdater with a given parser.
	 * @param parser the parser that parses the job data
	 */
	public JobUpdater(Parser parser)  {
		super(parser);
		sender = new Sender(ApiKeyInitializer.getApiKey());
	}
	
	/**
	 * Checks whether a job is already part of the database.
	 * @param job the event that is checked for
	 * @return true if the job is already saved, false if it is not
	 */
	private boolean containsJob(DataAccessJob job) {
		EntityManager em = DbHandlerService.getEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("FROM DataAccessJob WHERE title=:param1 AND url=:param2 AND short_info=:param3", DataAccessJob.class);
		q.setParameter("param1", job.getTitle());
		q.setParameter("param2", job.getUrl());
		q.setParameter("param3", job.getShortInfo());
		List<DataAccessJob> list = (List<DataAccessJob>) q.getResultList();
		em.close();
		if (!list.isEmpty())
			return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#dataChanged(java.util.List)
	 */
	@Override
	public boolean dataChanged(List<DataAccessObject> items) {
		List<DataAccessJob> load = DbHandlerService.getAllJobs();
		if (load.isEmpty()) 
			return true;
		if (items.size() > load.size())
			return true;
		for (int i = 0; i < items.size(); i++) {
			if (!containsJob((DataAccessJob) items.get(i))) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#selectChangedItems(java.util.List)
	 */
	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> items) {
		List<DataAccessJob> load = DbHandlerService.getAllJobs();
		List<DataAccessObject> changed = new ArrayList<DataAccessObject>();
		if (load.isEmpty())
			return items;
		DataAccessJob last = load.get(load.size() - 1);
		for (int i = 0; i < items.size(); i++) {
			if (!containsJob((DataAccessJob) items.get(i)))
				changed.add(items.get(i));
		}
		return changed;
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#updateDb(java.util.List)
	 */
	@Override
	public boolean updateDb(List<DataAccessObject> items) {
		for (int i = items.size() - 1; i >= 0; i--) {
			DataAccessJob jobs = (DataAccessJob) items.get(i);
			DbHandlerService.saveJob(jobs);
		}
		return true;
	}

	/**
	 * Sends a notification to every user device that activated the notification feature, for every job where the user 
	 * is interested in, with informations about that job.
	 * @param items the list with all new jobs
	 */
	@Override
	public void sendNotification(final List<DataAccessObject> items) {
		new Thread(new Runnable() {

			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				for (DataAccessObject item : items) {
					
					List<String> userRegIds = new ArrayList<String>();
					DataAccessJob job = (DataAccessJob) item;
					List<DataAccessUser> users = DbHandlerService.getUsersByTag(job.getTags().get(0));
					for(DataAccessUser user : users) {
						userRegIds.add(user.getClientId());
					}

					if (userRegIds.isEmpty()) {
						continue;
					}
					logger.info("Already in sendNotification");
					logger.info("List size: " + items.size());
					
					MulticastResult multicastResult = null;
					Message message = new Message.Builder()
				          .collapseKey("job")
				          .timeToLive(3)
				          .delayWhileIdle(true)
				          .dryRun(false)
				          .addData("title", job.getTitle())
				          .addData("url", job.getUrl())
				          .build(); 
					try {
					    logger.info("Trying to send notification ...send()");
					    System.out.println(message.toString());
					    System.out.println(userRegIds.size());
						multicastResult = sender.send(message, userRegIds, 5);
					} catch (IOException e) {
						logger.info("Error posting messages" + e.toString());
						e.printStackTrace();
					}
					List<Result> results = multicastResult.getResults();
				    // analyze the results
				    for (int l = 0; l < userRegIds.size(); l++) {
				          String regId = userRegIds.get(l);
				          Result result = results.get(l);
				          String messageId = result.getMessageId();
				          logger.info("Messageid = " + messageId + "for device " + regId);
				          if (messageId != null) {
				            logger.info("Succesfully sent message to device: " + regId +
				                "; messageId = " + messageId);
				            String canonicalRegId = result.getCanonicalRegistrationId();
				            if (canonicalRegId != null) {
				              // same device has more than on registration id: update it
				              logger.info("canonicalRegId " + canonicalRegId);
				              DataAccessUser user = DbHandlerService.getUser(regId);
				              user.setClientId(canonicalRegId);
				              DbHandlerService.saveUser(user);
				              //Datastore.updateRegistration(regId, canonicalRegId);
				            }
				          } else {
				            String error = result.getErrorCodeName();
				            logger.info("Error code: " + error);
				            if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
				              // application has been removed from device - unregister it
				              logger.info("Unregistered device: " + regId);
				              DbHandlerService.deleteUser(regId);
				            } else {
				              logger.info("Error sending message to " + regId + ": " + error);
				            }
				          }
				        }
					
					}
				
				
			}
			
		}).start();
	}
	
}
