package edu.kit.isco.KitAlumniApp.server.dbservices;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import edu.kit.isco.KitAlumniApp.server.parser.EventParser;
import edu.kit.isco.KitAlumniApp.server.parser.JobParser;
import edu.kit.isco.KitAlumniApp.server.parser.NewsParser;
import edu.kit.isco.KitAlumniApp.server.updater.EventUpdater;
import edu.kit.isco.KitAlumniApp.server.updater.JobUpdater;
import edu.kit.isco.KitAlumniApp.server.updater.NewsUpdater;

/**
 * A servlet class that is loaded at server start to initialize different Updaters.
 * @author Alexander Mueller
 *
 */
public class UpdaterService extends HttpServlet {

	/**
	 * The periodic timeout between each update
	 */
	private static final long UPDATE_TIMEOUT = 20;
	
	/**
	 * A scheduled executor that periodically fires the updaters
	 */
	private ScheduledExecutorService executor;	
	
	/**
	 * Executed at server start. Initializes and starts the scheduled executor to execute the updaters.
	 */
	public void init(ServletConfig cfg) {
		executor = Executors.newScheduledThreadPool(3);
		executor.scheduleAtFixedRate(new JobUpdater(new JobParser()), 0, UPDATE_TIMEOUT, TimeUnit.MINUTES);
		executor.scheduleAtFixedRate(new NewsUpdater(new NewsParser()), 0, UPDATE_TIMEOUT, TimeUnit.MINUTES);
		executor.scheduleAtFixedRate(new EventUpdater(new EventParser()), 0, UPDATE_TIMEOUT, TimeUnit.MINUTES);	
	}
	
	/**
	 * Executed at server shutdown. Immediatly stops the executor threads.
	 */
	public void destroy() {
		executor.shutdownNow();
	}
	

}
