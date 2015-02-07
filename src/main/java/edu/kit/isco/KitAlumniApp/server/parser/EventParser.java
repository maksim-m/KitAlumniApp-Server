package edu.kit.isco.KitAlumniApp.server.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;

public class EventParser implements Parser<DataAccessEvent> {
	
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

	
	
	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.parser.HtmlParser#parseContent()
	 */
	public ArrayList<DataAccessEvent> parseContent() {
		// TODO Auto-generated method stub
		return null;
	}



	private List<DataAccessEvent> parseEventsList() {
		List<DataAccessEvent> events = new ArrayList<DataAccessEvent>();
		
		Element contentDiv = doc.getElementById("content");
		Element table = contentDiv.select("table").first();
		table = table.select("tbody").first();
		Elements tableRows = table.select("tr");
		for (int i = 1; i < tableRows.size(); i++) {
			Element tableRow = tableRows.get(i);
			Element eventDateDiv = tableRow.getElementsByClass("datum").first();
			String eventDate = eventDateDiv.text();
			System.out.print(eventDate + " ");
			Element eventTimeDiv = tableRow.getElementsByClass("time").first();
			String eventTime = eventTimeDiv.text();
			// TODO: convert Date and Time to Calendar
			System.out.print(eventTime + " ");
			Element a = tableRow.select("a[href]").first();
			String eventUrl = a.attr("abs:href");
			String eventName = a.text();
			
			Element eventType = tableRow.getElementsByClass("eventtype").first();
			String eventShortInfo = "";
			if (eventType != null) {
				eventShortInfo = eventType.text();
			}
			
			DataAccessEvent event = new DataAccessEvent(eventName, eventShortInfo, eventUrl, null);
			events.add(event);
		}		
		
		return events;
	}

}
