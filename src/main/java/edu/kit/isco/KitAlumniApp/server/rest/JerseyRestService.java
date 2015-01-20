package edu.kit.isco.KitAlumniApp.server.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.parser.HtmlParser;
import edu.kit.isco.KitAlumniApp.server.parser.KitNewsParser;


@Path("/service")
public class JerseyRestService {
	
	@GET
	@Path("/news")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<DataAccessNews> produseJSON(  ) {
			HtmlParser<DataAccessNews> kitNewsParser = new KitNewsParser();
	        kitNewsParser.init();
	        ArrayList<DataAccessNews> news = kitNewsParser.parseContent();
			
			return news;
		
	}
}
