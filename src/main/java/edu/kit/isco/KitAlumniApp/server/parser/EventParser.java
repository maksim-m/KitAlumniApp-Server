package edu.kit.isco.KitAlumniApp.server.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;

public class EventParser implements Parser {
	
	private static final String SITE_URL = "http://www.intl.kit.edu/iforscher/5980.php/list";
	private ArrayList<DataAccessEvent> events;
	private Document doc = null;
	

	public void init() {
		events = new ArrayList<DataAccessEvent>();
		try {
            doc = Jsoup.connect(SITE_URL).get();
        } catch (IOException e) {
            // TODO Throws exception
        }		
	}

	public List<DataAccessObject> parseContent() {
		// TODO Auto-generated method stub
		return null;
	}

}
