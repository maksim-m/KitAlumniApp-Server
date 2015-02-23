package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;

/**
 * An Updater class that periodically parses event data from a website, checks for new events and saves them in the database.
 * @author Alexander Mueller
 *
 */
public class EventUpdater extends AbstractUpdater {

	/**
	 * Creates a new EventUpdater with a given parser.
	 * @param parser the parser that parses the event data
	 */
	public EventUpdater(Parser parser) {
		super(parser);
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#dataChanged(java.util.List)
	 */
	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessEvent> load = DbHandlerService.getAllEvents();
		if (load.isEmpty()) 
			return true;
		return !((DataAccessEvent)list.get(0)).equals(load.get(load.size() - 1));
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#selectChangedItems(java.util.List)
	 */
	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> list) {
		List<DataAccessEvent> load = DbHandlerService.getAllEvents();
		if (load.isEmpty())
			return list;
		DataAccessEvent last = load.get(load.size() - 1);
		int i = 0;
		while (i < list.size() && !last.equals(list.get(i))) {
			i++;
		}
		
		return list.subList(0, i);
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#updateDb(java.util.List)
	 */
	@Override
	public boolean updateDb(List<DataAccessObject> items) {
		while (!items.isEmpty()) {
			DataAccessEvent news = (DataAccessEvent) items.remove(items.size() - 1);
			DbHandlerService.saveEvent(news);
		}
		return true;
	}
}
