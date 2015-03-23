package edu.kit.isco.KitAlumniApp.server.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessTag;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessUser;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;


/**
 * This class represents the interface for the user devices.
 * @author Alexander Mueller
 *
 */
/**
 * @author Moony
 *
 */
@Path("/service")
public class JerseyRestService {
	
	/**
	 * Represents a HTTP-GET request that returns the latest news. 
	 * @see edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService#getLatestNews(long)
	 * @param id the id for which news are searched. If id is not set or id < 0 then the last 30 news will be returned
	 * @return a list of news
	 */
	@GET
	@Path("/news/latest")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessNews> latestNewsById(@DefaultValue("-1") @QueryParam("id") long id) {
		List<DataAccessNews> news = null;
		if (id < 0) {
			news = DbHandlerService.getAllNews();
			if (news.size() > 30)
				news = news.subList(news.size() - 30, news.size());
		} else {
			news = DbHandlerService.getLatestNews(id);
		}
		return news;
	}
	
	/**
	 * Represents a HTTP-GET request that returns an amount of news prior to an id
	 * @see edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService#getPreviousNews(long, long)
	 * @param id the id for which news are searched
	 * @param count the amount of news 
	 * @return a list of news
	 */
	@GET
	@Path("/news/previous")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessNews> previousNews(@QueryParam("id") long id, @QueryParam("count") long count) {
		List<DataAccessNews> news = null;
		if (id >= 0 && count > 0) {
			news = DbHandlerService.getPreviousNews(id, count);
		} else {
			news = DbHandlerService.getAllNews();
		}
		return news;
	}
	
	/**
	 * Represents a HTTP-GET request that returns the latest jobs.
	 * @see edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService#getLatestJobs(long)
	 * @param id the id for which jobs are searched. If id is not set or id < 0 then the last 30 jobs will be returned
	 * @return a list of jobs
	 */
	@GET
	@Path("/jobs/latest")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessJob> latestJobs(@DefaultValue("-1") @QueryParam("id") long id) {
		List<DataAccessJob> jobs = null;
		if (id < 0) {
			jobs = DbHandlerService.getAllJobs();
			if (jobs.size() > 30)
				jobs = jobs.subList(jobs.size() - 30, jobs.size());
		} else {
			jobs = DbHandlerService.getLatestJobs(id);
		}
		return jobs;
	}
	
	/**
	 * Represents a HTTP-GET request that returns an amount of jobs prior to an id
	 * @see edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService#getPreviousJobs(long, long)
	 * @param id the id for which jobs are searched
	 * @param count the amount of jobs 
	 * @return a list of news
	 */
	@GET
	@Path("/jobs/previous")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessJob> previousJobs(@QueryParam("id") long id, @QueryParam("count") long count) {
		List<DataAccessJob> jobs = null;
		if (id >= 0 && count > 0) {
			jobs = DbHandlerService.getPreviousJobs(id, count);
		} else {
			jobs = DbHandlerService.getAllJobs();
		}
		return jobs;
	}
	
	/**
	 * Represents a HTTP-GET request that returns all events in the near future.
	 * @return a list of events
	 */
	@GET
	@Path("/events")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessEvent> nextEvents() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, 1);		
		List<DataAccessEvent> events = DbHandlerService.getAllEvents();
		for (DataAccessEvent e : events) {
			if (e.getDate().getTimeInMillis() > date.getTimeInMillis() &&
					e.getDate().getTimeInMillis() < Calendar.getInstance().getTimeInMillis()) {
					events.remove(e);
				DbHandlerService.deleteEvent(e);
			}
		}
		return events;
	}
	
	
	/**
	 * Represents a HTTP-POST request that registers a given user.
	 * @param user the user that is registered
	 */
	@POST
	@Path("/users")
	@Consumes("application/json")
	public void registerUser(DataAccessUser user) {
		ArrayList<DataAccessTag> list = new ArrayList<DataAccessTag>();
		for (DataAccessTag t : user.getTags()) {
			list.add(DataAccessTag.getTagByName(t.getName()));
		}
		DbHandlerService.saveUser(new DataAccessUser(user.getClientId(), list, user.getPassword()));
	}
	
	
	/**
	 * Represents a HTTP-PUT requests that updates a given user.
	 * @param user the user that is updated
	 */
	@PUT
	@Path("/users")
	@Consumes("application/json")
	public void updateUser(DataAccessUser user) {
		DataAccessUser prev = DbHandlerService.getUser(user.getClientId());
		if (prev.getPassword().equals(user.getPassword())) {
			prev.setClientId(user.getClientId());
			prev.setPassword(user.getPassword());
			ArrayList<DataAccessTag> list = new ArrayList<DataAccessTag>();
			for (DataAccessTag t : user.getTags()) {
				list.add(DataAccessTag.getTagByName(t.getName()));
			}
			prev.setTags(list);
			DbHandlerService.saveUser(prev);
		}
	}
	
	/**
	 * Represents a HTTP-DELETE request that unregisters/deletes a given user.
	 * @param user the user that is unregistered/deleted
	 */
	@DELETE
	@Path("/users")
	@Consumes("application/json")
	public void deleteUser(DataAccessUser user) {
		DataAccessUser prev = DbHandlerService.getUser(user.getClientId());
		if (prev.getPassword().equals(user.getPassword())) {
			DbHandlerService.deleteUser(user.getClientId());
		}
	}
	
}
