package edu.kit.isco.KitAlumniApp.server.parser;

import java.util.ArrayList;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;

public class KitNewsParserTest {
	public static void main(String args[]) {
        HtmlParser<DataAccessNews> kitNewsParser = new KitNewsParser();
        kitNewsParser.init();
        
        ArrayList<DataAccessNews> news = kitNewsParser.parseContent();
        

        for (DataAccessNews n : news) {
            System.out.println("Title: " + n.getTitle());
            System.out.println("Short Description: " + n.getShortInfo());
            System.out.println("URL: " + n.getUrl());
            System.out.println("Image URL: " + n.getImageUrl());
            System.out.println("-----");
        }

        System.out.println("\nTotal: " + news.size() + " news.");
    }
}
