package edu.kit.isco.KitAlumniApp.server.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;

/**
 * News parser.
 * @version 0.1
 */
public class KitNewsParser implements HtmlParser<DataAccessNews> {
	
	private String siteUrl;
	private ArrayList<DataAccessNews> newsList;
	private Document doc = null;
	
	
	/**
	 * 
	 */
	public KitNewsParser() {
		super();
	}

	/**
	 * @see edu.kit.isco.KitAlumniApp.server.parser.HtmlParser#init()
	 */
	public void init() {
		newsList = new ArrayList<DataAccessNews>();
		// TODO Parse URL to news site
		siteUrl = "http://www.kit.edu/kit/english/news_2014.php";
		try {
            doc = Jsoup.connect(siteUrl).get();
        } catch (IOException e) {
            // TODO Throws exception
        }
	}

	/** 
	 * @see edu.kit.isco.KitAlumniApp.server.parser.HtmlParser#parseContent()
	 * @return Array with News
	 */
	public ArrayList<DataAccessNews> parseContent() {
		Element table = doc.select("table[class=tabelle3]").first();
        for (Element tr : table.select("tr")) {
            Element td = tr.select("td").first();       

            // edu.kit.isco.KitAlumniApp.dataStructures.News title, link
            Element b = td.select("b").first();
            Element a = b.select("a[href]").first();
            
            String link;
            String title;
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
            DataAccessNews news = new DataAccessNews(title, text, link);
            
            // image
            Element image = td.select("img[src]").first();
            String imageUrl = null;  
            if (image != null) {
            	imageUrl = image.absUrl("src");
            	news.setImageUrl(imageUrl);
            }  

            newsList.add(news);
            
        }
        return newsList;
	}

	

}
