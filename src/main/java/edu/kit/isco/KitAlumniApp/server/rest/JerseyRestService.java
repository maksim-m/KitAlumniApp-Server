package edu.kit.isco.KitAlumniApp.server.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessUser;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;


@Path("/service")
public class JerseyRestService {
	
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
	
	@GET
	@Path("/news/previous")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessNews> previousNews(@QueryParam("id") long id, @QueryParam("count") long count) {
		List<DataAccessNews> news = null;
		if (id >= 0 && count > 0) {
			news = DbHandlerService.getPreviousNews(id, count);
		}
		return news;
	}
	
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
	
	@GET
	@Path("/jobs/previous")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessJob> previousJobs(@QueryParam("id") long id, @QueryParam("count") long count) {
		List<DataAccessJob> jobs = null;
		if (id >= 0 && count > 0) {
			jobs = DbHandlerService.getPreviousJobs(id, count);
		}
		return jobs;
	}
	
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
			}
		}
		return events;
	}
	
	
	@POST
	@Path("/users/{user}")
	@Consumes("application/json")
	public void registerUser(@PathParam("user") DataAccessUser user) {
		DbHandlerService.saveUser(user);
	}
	
	@PUT
	@Path("users/{user}")
	@Consumes("application/json")
	public void updateUser(@PathParam("user") DataAccessUser user) {
		DataAccessUser prev = DbHandlerService.getUser(user.getClientId());
		prev.setClientId(user.getClientId());
		prev.setPassword(user.getPassword());
		prev.setTags(user.getTags());
		DbHandlerService.saveUser(prev);
	}
	
	 @DELETE
	 @Path("users/{user}")
	 @Consumes("application/json")
	 public void deleteUser(@PathParam("user") DataAccessUser user) {
		 DbHandlerService.deleteUser(user.getClientId());
	 }
	
}
