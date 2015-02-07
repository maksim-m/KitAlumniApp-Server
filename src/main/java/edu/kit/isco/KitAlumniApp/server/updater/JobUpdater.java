package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.parser.JobParser;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;

public class JobUpdater extends AbstractUpdater {
	
	public JobUpdater(JobParser parser)  {
		super(parser);
	}
	
	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessJob> load = DbHandlerService.getAllJobs();
		return load.get(load.size()).equals(list.get(list.size()));
	}

	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> list) {
		List<DataAccessJob> load = DbHandlerService.getAllJobs();
		DataAccessJob last = load.get(load.size() - 1);
		
		int size = list.size() - 1;
		while (size >= 0 && !last.equals(list.get(size)))
			size--;
		
		return list.subList(size, list.size() - 1);
	}

	@Override
	public boolean updateDb(List<DataAccessObject> list) {
		for (DataAccessObject job : list) {
			DbHandlerService.saveJob((DataAccessJob) job);
		}
		return true;
	}

	@Override
	public void sendNotification(List<DataAccessObject> list) {
		// TODO Auto-generated method stub
		
	}

	
}
