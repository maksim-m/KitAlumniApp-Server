package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import javax.servlet.ServletContext;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.parser.JobParser;
import edu.kit.isco.KitAlumniApp.server.parser.Parser;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;

public class JobUpdater extends AbstractUpdater {
	
	public JobUpdater(Parser parser)  {
		super(parser);
	}
	
	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessJob> load = DbHandlerService.getAllJobs();
		if (load.isEmpty()) 
			return true;
		return !((DataAccessJob)list.get(0)).equals(load.get(load.size() - 1));
	}

	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> list) {
		List<DataAccessJob> load = DbHandlerService.getAllJobs();
		if (load.isEmpty())
			return list;
		DataAccessJob last = load.get(load.size() - 1);
		int i = 0;
		while (i < list.size() && !last.equals(list.get(i))) {
			i++;
		}
		return list.subList(0, i);
	}

	@Override
	public boolean updateDb(List<DataAccessObject> items) {
		while (!items.isEmpty()) {
			DataAccessJob jobs = (DataAccessJob) items.remove(items.size() - 1);
			DbHandlerService.saveJob(jobs);
		}
		return true;
	}

	@Override
	public void sendNotification(List<DataAccessObject> list) {
		// TODO Auto-generated method stub
		
	}

	
}
