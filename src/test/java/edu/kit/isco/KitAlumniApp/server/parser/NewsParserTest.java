package edu.kit.isco.KitAlumniApp.server.parser;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NewsParserTest {
	
	NewsParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new NewsParser();
		parser.init();
	}

	@Test
	public void testInit() {
		parser.init();
	}

	@Test
	public void testParseContent() {
		Assert.assertNotNull(parser.parseContent());
	}

}
