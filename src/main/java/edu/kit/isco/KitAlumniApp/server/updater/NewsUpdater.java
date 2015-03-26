package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;

/**
 * An Updater class that periodically parses event data from a website, checks for new news and saves them in the database.
 * @author Alexander Mueller
 *
 */
public class NewsUpdater extends AbstractUpdater {
	
	/**
	 * Creates a new NewsUpdater with a given parser.
	 * @param parser the parser that parses the news data
	 */
	public NewsUpdater(Parser parser) {
		super(parser);
	}
	
	/**
	 * Checks whether a news is already part of the database.
	 * @param job the news that is checked for
	 * @return true if the news is already saved, false if it is not
	 */
	private boolean containsNews(DataAccessNews news) {
		EntityManager em = DbHandlerService.getEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("FROM DataAccessJob WHERE title=:param1 AND url=:param2", DataAccessNews.class);
		q.setParameter("param1", news.getTitle());
		q.setParameter("param2", news.getUrl());
		List<DataAccessNews> list = (List<DataAccessNews>) q.getResultList();
		em.close();
		if (!list.isEmpty())
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#dataChanged(java.util.List)
	 */
	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessNews> load = DbHandlerService.getAllNews();
		if (load.isEmpty()) 
			return true;
		if (list.size() > load.size())
			return true;
		for (int i = 0; i < list.size(); i++) {
			if (!containsNews((DataAccessNews) list.get(i))) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#selectChangedItems(java.util.List)
	 */
	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> list) {
		List<DataAccessNews> load = DbHandlerService.getAllNews();
		List<DataAccessObject> changed = new ArrayList<DataAccessObject>();
		if (load.isEmpty())
			return list;
		DataAccessNews last = load.get(load.size() - 1);
		for (int i = 0; i < list.size(); i++) {
			if (!containsNews((DataAccessNews) list.get(i)))
				changed.add(list.get(i));
		}
		return changed;
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#updateDb(java.util.List)
	 */
	@Override
	public boolean updateDb(List<DataAccessObject> items) {
		while (!items.isEmpty()) {
			DataAccessNews news = (DataAccessNews) items.remove(items.size() - 1);
			DbHandlerService.saveNews(news);
		}
		return true;
	}
}
