package edu.kit.isco.KitAlumniApp.server.parser;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobParserTest {
	
	JobParser parser;
	
	@Before
	public void setUp() throws Exception {
		parser = new JobParser();
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
