package edu.kit.isco.KitAlumniApp.server.dbservices;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.parser.HtmlParser;
import edu.kit.isco.KitAlumniApp.server.parser.KitNewsParser;

public class DbHandlerServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	@Test
	public void testGetAll() {
		HtmlParser<DataAccessNews> kitNewsParser = new KitNewsParser();
        kitNewsParser.init();
        
        ArrayList<DataAccessNews> parsedNews = kitNewsParser.parseContent();
        
        DbHandlerService.saveNews(parsedNews);
        
        List<DataAccessNews> newsFromDb = DbHandlerService.getAllNews();
        
        Assert.assertTrue(parsedNews.equals(newsFromDb));
	}
	*/

}
