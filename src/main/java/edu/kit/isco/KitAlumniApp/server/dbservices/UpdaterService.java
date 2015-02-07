package edu.kit.isco.KitAlumniApp.server.dbservices;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kit.isco.KitAlumniApp.server.parser.EventParser;
import edu.kit.isco.KitAlumniApp.server.parser.JobParser;
import edu.kit.isco.KitAlumniApp.server.parser.NewsParser;
import edu.kit.isco.KitAlumniApp.server.updater.EventUpdater;
import edu.kit.isco.KitAlumniApp.server.updater.JobUpdater;
import edu.kit.isco.KitAlumniApp.server.updater.NewsUpdater;

public class UpdaterService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
	private static final long UPDATE_TIMEOUT = 20;
	
	public void init(ServletConfig cfg) {
		executor.scheduleAtFixedRate(new JobUpdater(new JobParser()), 1, UPDATE_TIMEOUT, TimeUnit.MINUTES);
		executor.scheduleAtFixedRate(new NewsUpdater(new NewsParser()), 1, UPDATE_TIMEOUT, TimeUnit.MINUTES);
		executor.scheduleAtFixedRate(new EventUpdater(new EventParser()), 1, UPDATE_TIMEOUT, TimeUnit.MINUTES);
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {}

}
