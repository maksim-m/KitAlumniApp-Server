package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

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

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#dataChanged(java.util.List)
	 */
	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessNews> load = DbHandlerService.getAllNews();
		if (load.isEmpty()) 
			return true;
		return !((DataAccessNews)list.get(0)).equals(load.get(load.size() - 1));
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.updater.AbstractUpdater#selectChangedItems(java.util.List)
	 */
	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> list) {
		List<DataAccessNews> load = DbHandlerService.getAllNews();
		if (load.isEmpty())
			return list;
		DataAccessNews last = load.get(load.size() - 1);
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
			DataAccessNews news = (DataAccessNews) items.remove(items.size() - 1);
			DbHandlerService.saveNews(news);
		}
		return true;
	}
}
