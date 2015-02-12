package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import javax.servlet.ServletContext;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.parser.NewsParser;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;

public class NewsUpdater extends AbstractUpdater {
	
	public NewsUpdater(Parser parser) {
		super(parser);
	}

	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessNews> load = DbHandlerService.getAllNews();
		if (load.isEmpty()) 
			return true;
		return !((DataAccessNews)list.get(0)).equals(load.get(load.size() - 1));
	}

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

	@Override
	public boolean updateDb(List<DataAccessObject> items) {
		while (!items.isEmpty()) {
			DataAccessNews news = (DataAccessNews) items.remove(items.size() - 1);
			DbHandlerService.saveNews(news);
		}
		return true;
	}

	@Override
	public void sendNotification(List<DataAccessObject> list) {
		// TODO Auto-generated method stub
		
	}

}
