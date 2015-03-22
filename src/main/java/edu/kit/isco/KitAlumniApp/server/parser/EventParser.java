package edu.kit.isco.KitAlumniApp.server.parser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;

/**
 * Class that parsers events from a specific web page.
 * @author Maksim Moiseikin
 *
 */
public class EventParser implements Parser<DataAccessEvent> {
	
	/**
	 * Container for parsed events
	 */
	private ArrayList<DataAccessEvent> events;
	
	/**
	 * the HTML-Document
	 */
	private Document doc = null;
	
	/**
	 * The site url where the events are parsed from
	 */
	private static final String SITE_URL = "http://www.intl.kit.edu/iforscher/5980.php/list";

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.parser.Parser#init()
	 */
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
		Elements c = doc.getElementsByClass("veranstaltungen");
		Element a = c.select("a[href]").get(1);
		String n = a.attr("abs:href");
		n = n.substring(n.length() - 3, n.length() - 1);
		int count = Integer.parseInt(n) / 12;
		for (int i = 0; i <= count; i++) {
			try {
				doc = Jsoup.connect(SITE_URL + "/" + Integer.toString(12 * i) + "?").get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parseEventsList();
		}
		parseEventsDetails(events);
		return events;
	}



	/**
	 * This method parses events with their short details and returns a list of all parsed events.
	 * @return a list of parsed events
	 */
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
			
			DataAccessEvent event = new DataAccessEvent(eventName, eventShortInfo, "", eventUrl, calendar);
			events.add(event);
		}		
		
		return events;
	}
	
	/**
	 * This method parses the full description for every event of a given list of events.
	 * @param events all previously parsed events
	 */
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
			StringBuilder htmlText = new StringBuilder();
			htmlText.append("<!doctype html><html lang=\"en\"><head><meta charset=\"utf-8\">");
			htmlText.append("<title>");
			htmlText.append(event.getTitle());
			htmlText.append("</title></head><body>");
			Element contentDiv = eventSite.getElementById("content");
			Element table = contentDiv.select("table").first();
			table = table.select("tbody").first();
			if (table != null) {
				Elements urls = table.select("a[href]");
				for(Element url : urls)	{
				    url.attr("href", url.absUrl("href"));
				}
				htmlText.append(table.html());
			} else {
				htmlText.append("Sorry, an error occurred while loading the content.");
			}				
			htmlText.append("</body></html>");
			event.setAllText(htmlText.toString());
		}
	}
}