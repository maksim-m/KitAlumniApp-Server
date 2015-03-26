package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	/**
	 * Checks whether an event is already part of the database.
	 * @param event the event that is checked for
	 * @return true if the event is already saved, false if it is not
	 */
	private boolean containsEvent(DataAccessEvent event) {
		EntityManager em = DbHandlerService.getEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("FROM DataAccessEvent WHERE title=:param1 AND url=:param2", DataAccessEvent.class);
		q.setParameter("param1", event.getTitle());
		q.setParameter("param2", event.getUrl());
		List<DataAccessEvent> list = (List<DataAccessEvent>) q.getResultList();
		em.close();
		if (!list.isEmpty())
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#dataChanged(java.util.List)
	 */
	@Override
	public boolean dataChanged(List<DataAccessObject> items) {
		List<DataAccessEvent> load = DbHandlerService.getAllEvents();
		if (load.isEmpty()) 
			return true;
		if (items.size() > load.size())
			return true;
		for (int i = 0; i < items.size(); i++) {
			if (!containsEvent((DataAccessEvent) items.get(i))) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#selectChangedItems(java.util.List)
	 */
	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> items) {
		List<DataAccessEvent> load = DbHandlerService.getAllEvents();
		List<DataAccessObject> changed = new ArrayList<DataAccessObject>();
		if (load.isEmpty())
			return items;
		DataAccessEvent last = load.get(load.size() - 1);
		for (int i = 0; i < items.size(); i++) {
			if (!containsEvent((DataAccessEvent) items.get(i)))
				changed.add(items.get(i));
		}
		return changed;
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#updateDb(java.util.List)
	 */
	@Override
	public boolean updateDb(List<DataAccessObject> items) {
		while (!items.isEmpty()) {
			DataAccessEvent event = (DataAccessEvent) items.remove(items.size() - 1);
			DbHandlerService.saveEvent(event);
		}
		return true;
	}
}