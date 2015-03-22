package edu.kit.isco.KitAlumniApp.server.rest;

import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.Assert;

import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessTag;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessUser;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;

public class JerseyRestTest extends JerseyTest {


	/* (non-Javadoc)
	 * @see com.sun.jersey.test.framework.JerseyTest#configure()
	 */
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	private void setUpDatabase() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void testGetNews() {
		
	}
	

}
