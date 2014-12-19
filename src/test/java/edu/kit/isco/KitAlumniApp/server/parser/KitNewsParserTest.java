package edu.kit.isco.KitAlumniApp.server.parser;

import java.util.ArrayList;

import edu.kit.isco.KitAlumniApp.server.datastructures.News;

public class KitNewsParserTest {
	public static void main(String args[]) {
        HtmlParser<News> kitNewsParser = new KitNewsParser();
        kitNewsParser.init();
        
        ArrayList<News> news = kitNewsParser.parseContent();
        

        for (News n : news) {
            System.out.println("Title: " + n.getTitle());
            System.out.println("Text: " + n.getShortDescription());
            System.out.println("URL: " + n.getUrl());
            System.out.println("-----");
        }

        System.out.println("\nTotal: " + news.size() + " news.");
    }
}
