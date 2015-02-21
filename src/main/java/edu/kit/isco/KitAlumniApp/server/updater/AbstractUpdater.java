package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import javax.servlet.ServletContext;

import org.jboss.logging.Logger;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;

public abstract class AbstractUpdater implements Runnable {
	
	private ServletContext context;
	private Parser parser;
	
	protected final Logger logger = Logger.getLogger(getClass().getName());
	
	public AbstractUpdater(Parser parser) {
		this.parser = parser;
	}
	
	public void run() {
		parser.init();
		List<DataAccessObject> items = parser.parseContent();
		if (dataChanged(items)) {
			items = this.selectChangedItems(items);
			sendNotification(items);	
			this.updateDb(items);	
		}
	}
	
	public abstract boolean dataChanged(List<DataAccessObject> items);
	public abstract List<DataAccessObject> selectChangedItems(List<DataAccessObject> items);
	public abstract boolean updateDb(List<DataAccessObject> items);
	public abstract void sendNotification(List<DataAccessObject> items);

}
