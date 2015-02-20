package edu.kit.isco.KitAlumniApp.server.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;

/**
 * News parser.
 * @version 0.1
 */
public class NewsParser implements Parser<DataAccessNews> {
	
	private static String kitNewsSitePattern = "^(https?)://www.kit.edu/.*\\d.*.php";
	private String siteUrl;
	private ArrayList<DataAccessNews> newsList;
	private Document doc = null;
	private boolean firstTime = false;
	
	
	/**
	 * 
	 */
	public NewsParser() {
		super();
	}

	/**
	 * @see edu.kit.isco.KitAlumniApp.server.parser.Parser#init()
	 */
	public void init() {
		newsList = new ArrayList<DataAccessNews>();
		// TODO Parse URL to news site
		siteUrl = "http://www.kit.edu/kit/english/news_" + Integer.toString(Calendar.getInstance().get(Calendar.YEAR)) + ".php";
		try {
            doc = Jsoup.connect(siteUrl).get();
        } catch (IOException e) {
            // TODO Throws exception
        }
	}

	/** 
	 * @see edu.kit.isco.KitAlumniApp.server.parser.Parser#parseContent()
	 * @return Array with News
	 */
	public ArrayList<DataAccessNews> parseContent() {
		if (firstTime) {
			Element box = doc.getElementById("menu-box");
			Element media = box.select("ul").first();
			Element list = media.select("li").get(3);
			media = list.select("ul").first();
			list = media.select("li").first();
			media = list.select("ul").first();
			firstTime = !firstTime;
			for (Element e : media.select("li")) {
				Element url = e.select("a[href]").first();
				siteUrl = url.attr("abs:href");
				try {
					doc = Jsoup.connect(siteUrl).get();
				} catch (IOException e1) {
					continue;
				}
				parseContent();
			}
		} else {
			Element table = doc.select("table[class=tabelle3]").first();
	        for (Element tr : table.select("tr")) {
	            Element td = tr.select("td").first();
	
	            // edu.kit.isco.KitAlumniApp.dataStructures.News title, link
	            Element b = td.select("b").first();
	            Element a = b.select("a[href]").first();
	            Element c = td.select("span").first();
	            
	            Calendar date = null;
	            try {
	            	date = parseDate(c.text());
	            } catch (Exception e) {
	            }
	            String link ="";
	            String title ="";
	            if (a != null) {
	                link = a.attr("abs:href");
	                title = a.text();
	            } else {
	            	// something wrong!
	                link = "";
	                title = "";
	            } 
	            
	
	            // Short description
	            Element p = td.select("p").first();
	            String text = null;
	            if (p != null) {
	                text = p.text();
	            } else {
	                text = td.text();
	            }
	            DataAccessNews news = new DataAccessNews(title, text, "", link, "", date);
	            
	            // image
	            Element image = td.select("img[src]").first();
	            String imageUrl = null;  
	            if (image != null) {
	            	imageUrl = image.absUrl("src");
	            	news.setImageUrl(imageUrl);
	            } 
	            newsList.add(news);
            	
	        }
	    }
		parseNewsDetail(newsList);
        return newsList;
	}

	private Calendar parseDate(String date) {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(date.substring(7, 11)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(1, 3)));
		return c;
	}
	
	private void parseNewsDetail(ArrayList<DataAccessNews> news) {
		Document newsSite = null;
		for(DataAccessNews n : news) {
			
			if (!n.getUrl().matches(kitNewsSitePattern)) {
				n.setAllText(null);
				continue;
			}			
			
			try {
				newsSite = Jsoup.connect(n.getUrl()).get();
	        } catch (IOException e) {
	            // TODO Throws exception
	        	continue;
	        }
			StringBuilder htmlText = new StringBuilder();
			htmlText.append("<!doctype html><html lang=\"en\"><head><meta charset=\"utf-8\">");
			htmlText.append("<title>");
			htmlText.append(n.getTitle());
			htmlText.append("</title></head><body>");
			Element contentDiv = newsSite.getElementById("content");
			htmlText.append("</body></html>");
			n.setAllText(contentDiv.html());
		}
	}
	

}
