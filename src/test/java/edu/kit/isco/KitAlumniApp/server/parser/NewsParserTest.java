package edu.kit.isco.KitAlumniApp.server.parser;

import java.util.ArrayList;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;

public class NewsParserTest {
	public static void main(String args[]) {
        Parser<DataAccessNews> kitNewsParser = new NewsParser();
        kitNewsParser.init();
        
        ArrayList<DataAccessNews> news = kitNewsParser.parseContent();
        
        int counter = 0;
        for (DataAccessNews n : news) {
        	counter++;
            System.out.println("Title: " + n.getTitle());
            System.out.println("Short Description: " + n.getShortInfo());
            System.out.println("URL: " + n.getUrl());
            System.out.println("Image URL: " + n.getImageUrl());
            System.out.println("-----");
            System.out.println("-----");
            System.out.println("-----");
        }

        System.out.println("\nTotal: " + news.size() + " news.");
        System.out.println(counter);
    }
}
