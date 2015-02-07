package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;
import edu.kit.isco.KitAlumniApp.server.parser.EventParser;

public class EventUpdater extends AbstractUpdater {

	public EventUpdater(EventParser parser) {
		super(parser);
	}

	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessEvent> load = DbHandlerService.getAllEvents();
		return load.get(load.size()).equals(list.get(list.size()));
	}

	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> list) {
		List<DataAccessEvent> load = DbHandlerService.getAllEvents();
		DataAccessEvent last = load.get(load.size() - 1);
		
		int size = list.size() - 1;
		while (size >= 0 && !last.equals(list.get(size)))
			size--;
		
		return list.subList(size, list.size() - 1);
	}

	@Override
	public boolean updateDb(List<DataAccessObject> list) {
		for (DataAccessObject event : list) {
			DbHandlerService.saveEvent((DataAccessEvent) event);
		}
		return true;
	}

	@Override
	public void sendNotification(List<DataAccessObject> list) {
		// TODO Auto-generated method stub
		
	}
	

}
