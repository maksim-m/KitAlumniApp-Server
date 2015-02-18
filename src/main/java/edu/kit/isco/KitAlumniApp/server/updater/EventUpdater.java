package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import javax.servlet.ServletContext;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;
import edu.kit.isco.KitAlumniApp.server.parser.EventParser;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;

public class EventUpdater extends AbstractUpdater {

	public EventUpdater(Parser parser) {
		super(parser);
	}

	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessEvent> load = DbHandlerService.getAllEvents();
		if (load.isEmpty()) 
			return true;
		return !((DataAccessEvent)list.get(0)).equals(load.get(load.size() - 1));
	}

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

	@Override
	public boolean updateDb(List<DataAccessObject> items) {
		while (!items.isEmpty()) {
			DataAccessEvent news = (DataAccessEvent) items.remove(items.size() - 1);
			DbHandlerService.saveEvent(news);
		}
		return true;
	}

	@Override
	public void sendNotification(List<DataAccessObject> list) {
		// TODO Auto-generated method stub
		
	}
	

}
