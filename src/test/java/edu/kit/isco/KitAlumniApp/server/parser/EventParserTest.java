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

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() {
		parser.init();
	}
	
	@Test
	@Ignore
	public void testParseContent() {
		
		Assert.assertNull(parser.parseContent());
	}
	

}
