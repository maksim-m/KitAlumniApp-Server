package edu.kit.isco.KitAlumniApp.server.parser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
		events = parseEventsList();
		parseEventsDetails(events);
		return events;
	}



	private ArrayList<DataAccessEvent> parseEventsList() {		
		Element contentDiv = doc.getElementById("content");
		Element table = contentDiv.select("table").first();
		table = table.select("tbody").first();
		Elements tableRows = table.select("tr");
		for (int i = 1; i < tableRows.size(); i++) {
			Element tableRow = tableRows.get(i);
			Element eventDateDiv = tableRow.getElementsByClass("datum").first();
			String eventDate = eventDateDiv.text();
			Element eventTimeDiv = tableRow.getElementsByClass("time").first();
			String eventTime = eventTimeDiv.text();
			// TODO: convert Time to Calendar
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			try {
				calendar.setTime(sdf.parse(eventDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			Element a = tableRow.select("a[href]").first();
			String eventUrl = a.attr("abs:href");
			String eventName = a.text();
			
			Element eventType = tableRow.getElementsByClass("eventtype").first();
			String eventShortInfo = "";
			if (eventType != null) {
				eventShortInfo = eventType.text();
			}
			
			DataAccessEvent event = new DataAccessEvent(eventName, eventShortInfo, eventUrl, calendar);
			events.add(event);
		}		
		
		return events;
	}

	private void parseEventsDetails(ArrayList<DataAccessEvent> events) {
		Document eventSite = null;
		for(DataAccessEvent event : events) {
			try {
				eventSite = Jsoup.connect(event.getUrl()).get();
	        } catch (IOException e) {
	            // TODO Throws exception
	        	continue;
	        }
			if (eventSite == null) {
				continue;
			}
			Element contentDiv = doc.getElementById("content");
			Element table = contentDiv.select("table").first();
			table = table.select("tbody").first();
			event.setAllText(table.html());
		}
	}
}
