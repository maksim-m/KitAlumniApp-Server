package edu.kit.isco.KitAlumniApp.server.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class EventParserTest {
	
	EventParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new EventParser();
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
