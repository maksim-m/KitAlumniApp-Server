package edu.kit.isco.KitAlumniApp.server.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventParserTest {
	
	EventParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new EventParser();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() {
		parser.init();
	}

}
