package edu.kit.isco.KitAlumniApp.server.updater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessUser;
import edu.kit.isco.KitAlumniApp.server.parser.JobParser;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;
import edu.kit.isco.KitAlumniApp.server.gcm.Constants;
import edu.kit.isco.KitAlumniApp.server.gcm.Message;
import edu.kit.isco.KitAlumniApp.server.gcm.MulticastResult;
import edu.kit.isco.KitAlumniApp.server.gcm.Result;
import edu.kit.isco.KitAlumniApp.server.gcm.Sender;

public class JobUpdater extends AbstractUpdater {
	
	Sender sender;
	
	public JobUpdater(Parser parser)  {
		super(parser);
		sender = new Sender(yourApiKey);
	}
	
	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessJob> load = DbHandlerService.getAllJobs();
		if (load.isEmpty()) 
			return true;
		return !((DataAccessJob)list.get(0)).equals(load.get(load.size() - 1));
	}

	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> list) {
		List<DataAccessJob> load = DbHandlerService.getAllJobs();
		if (load.isEmpty())
			return list;
		DataAccessJob last = load.get(load.size() - 1);
		int i = 0;
		while (i < list.size() && !last.equals(list.get(i))) {
			i++;
		}
		return list.subList(0, i);
	}

	@Override
	public boolean updateDb(List<DataAccessObject> items) {
		while (!items.isEmpty()) {
			DataAccessJob jobs = (DataAccessJob) items.remove(items.size() - 1);
			DbHandlerService.saveJob(jobs);
		}
		return true;
	}

	@Override
	public void sendNotification(List<DataAccessObject> list) {
		List<DataAccessUser> users = DbHandlerService.getAllUsers();
		List<String> userRegIds = new ArrayList<String>();
		for(DataAccessUser user : users) {
			userRegIds.add(user.getClientId());
		}
		logger.info("Already in sendNotification");
		logger.info("List size: " + list.size());
		
		for (int i = 0; i < list.size(); i++) {
			DataAccessEvent job = (DataAccessEvent) list.get(i);
			MulticastResult multicastResult = null;
			Message message = new Message.Builder()
	          .collapseKey("job")
	          .timeToLive(3)
	          .delayWhileIdle(true)
	          .dryRun(false)
	          .addData("title", job.getTitle())
	          .build(); 
		    try {
		    	logger.info("Trying to send notification ...send()");
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
	
}
