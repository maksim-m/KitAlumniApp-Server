package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import javax.servlet.ServletContext;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;

public abstract class AbstractUpdater implements Runnable {
	
	private ServletContext context;
	private Parser parser;
	
	public AbstractUpdater(Parser parser) {
		this.parser = parser;
	}
	
	public void run() {
		parser.init();
		List<DataAccessObject> items = parser.parseContent();
		updateDb(items);
		/*if (dataChanged(items)) {
			items = this.selectChangedItems(items);
			this.updateDb(items);
			this.sendNotification(items);
		}*/
	}
	
	public abstract boolean dataChanged(List<DataAccessObject> items);
	public abstract List<DataAccessObject> selectChangedItems(List<DataAccessObject> items);
	public abstract boolean updateDb(List<DataAccessObject> items);
	public abstract void sendNotification(List<DataAccessObject> items);

}
