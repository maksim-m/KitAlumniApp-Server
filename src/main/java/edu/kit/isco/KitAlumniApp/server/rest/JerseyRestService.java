package edu.kit.isco.KitAlumniApp.server.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import edu.kit.isco.KitAlumniApp.server.parser.HtmlParser;
import edu.kit.isco.KitAlumniApp.server.parser.KitNewsParser;


@Path("/service")
public class JerseyRestService {
	
	@GET
	@Path("/news/latest")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessNews> latestNews(@QueryParam("id") long id, @QueryParam("count") long count) {
		return null;
	}
	
	@GET
	@Path("/news/previous")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessNews> previousNews(@QueryParam("id") long id, @QueryParam("count") long count) {
		return null;
	}
	
	@GET
	@Path("/jobs/latest")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessJob> latestJobs(@QueryParam("id") long id, @QueryParam("count") long count) {
		return null;
	}
	
	@GET
	@Path("/jobs/previous")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessJob> previousJobs(@QueryParam("id") long id, @QueryParam("count") long count) {
		return null;
	}
	
	@GET
	@Path("/events")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessEvent> nextEvents() {
		return null;
	}
	
	
	@POST
	@Path("/users/{user}")
	@Consumes("application/json")
	public void registerUser(@PathParam("user") DataAccessUser user) {
		
	}
	
	@PUT
	@Path("users/{user}")
	@Consumes("application/json")
	public void updateUser(@PathParam("user") DataAccessUser user) {
		
	}
	
	 @DELETE
	 @Path("users/{user}")
	 @Consumes("application/json")
	 public void deleteUser(@PathParam("user") DataAccessUser user) {
		 
	 }
	
}
