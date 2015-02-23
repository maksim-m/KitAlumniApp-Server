package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import org.jboss.logging.Logger;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;

/**
 * An abstract representation of an Updater. It periodically executes a parser and transfers possible new data into the database.
 * @author Alexander Mueller
 *
 */
public abstract class AbstractUpdater implements Runnable {
	
	/**
	 * The assigned parser that parses data.
	 */
	private Parser<DataAccessObject> parser;
	
	protected final Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * Creates a new AbstractUpdater object with a given Parser
	 * @param parser
	 */
	public AbstractUpdater(Parser<DataAccessObject> parser) {
		this.parser = parser;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		parser.init();
		List<DataAccessObject> items = parser.parseContent();
		if (dataChanged(items)) {
			items = this.selectChangedItems(items);
			sendNotification(items);	
			this.updateDb(items);	
		}
	}
	
	/**
	 * Checks for parsed data if there are inconsitencies with the database.
	 * @param items the parsed data
	 * @return true when there are inconsistencies, false otherwise
	 */
	public abstract boolean dataChanged(List<DataAccessObject> items);
	
	/**
	 * If inconsistencies exists, this selects the data out of the parsed data, that has to be transferred to the database
	 * @param items the parsed data
	 * @return the data that has to be transferred to the database
	 */
	public abstract List<DataAccessObject> selectChangedItems(List<DataAccessObject> items);
	
	/**
	 * Updates the database with given data selected by selectChangedItems().
	 * @param items the items that have to be saved into the database
	 * @return true if the execution was successful, false otherwise
	 */
	public abstract boolean updateDb(List<DataAccessObject> items);
	
	public void sendNotification(List<DataAccessObject> items) {}

}
