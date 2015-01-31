package edu.kit.isco.KitAlumniApp.server.parser;

import java.util.List;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;

public interface Parser {
	
	public void init();
	public List<DataAccessObject> parseContent();

}
