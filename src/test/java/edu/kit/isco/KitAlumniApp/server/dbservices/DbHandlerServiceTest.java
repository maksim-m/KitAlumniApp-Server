package edu.kit.isco.KitAlumniApp.server.dbservices;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessEvent;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessTag;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessUser;

public class DbHandlerServiceTest {
	/*
	
	public DataAccessTag[][] 	tags_list   = new DataAccessTag[10][];
	public DataAccessUser[] 	user		= new DataAccessUser[10];
	public DataAccessEvent[] 	event 		= new DataAccessEvent[10];
	public DataAccessNews[] 	news 		= new DataAccessNews[10];
	public DataAccessJob[] 		job 		= new DataAccessJob[10];
	public DbHandlerService     service     = new DbHandlerService();
	
	@Before
	public void setUp() throws Exception {
		service.contextInitialized(null);
	
		tags_list[0] = new DataAccessTag[]{DataAccessTag.ADMINISTRATION, DataAccessTag.DATA_ADMINISTRATION, DataAccessTag.THRESHOLD_WORKER};
		tags_list[1] = new DataAccessTag[]{DataAccessTag.CLERK, DataAccessTag.OTHERS, DataAccessTag.SALES_OCCUPATION, DataAccessTag.TECHNICAL_EMPLOYEE};
		tags_list[2] = new DataAccessTag[]{DataAccessTag.SCIENTIST, DataAccessTag.PROFESSOR, DataAccessTag.GRADUAND};
		tags_list[3] = new DataAccessTag[]{DataAccessTag.STUDENT_RESEARCH_PROJECT, DataAccessTag.DOCTORAND, DataAccessTag.OTHERS};
		tags_list[4] = new DataAccessTag[]{DataAccessTag.ENGINEER, DataAccessTag.INDUSTRIAL, DataAccessTag.TECHNICAL_EMPLOYEE};
		tags_list[5] = new DataAccessTag[]{DataAccessTag.TRAINEE};
		tags_list[6] = new DataAccessTag[]{DataAccessTag.INDUSTRIAL, DataAccessTag.ADMINISTRATION, DataAccessTag.SALES_OCCUPATION};
		tags_list[7] = new DataAccessTag[]{DataAccessTag.DATA_ADMINISTRATION, DataAccessTag.GRADUAND, DataAccessTag.TRAINEE};
		tags_list[8] = new DataAccessTag[]{DataAccessTag.CLERK, DataAccessTag.INDUSTRIAL};
		tags_list[9] = new DataAccessTag[]{DataAccessTag.SCIENTIST, DataAccessTag.DOCTORAND, DataAccessTag.TECHNICAL_EMPLOYEE};
		
		user[0] = new DataAccessUser("f3dapb3laf210g3kf_kj3%3", Arrays.asList(tags_list[3]),"testpassword_1");
		user[1] = new DataAccessUser("34pad9vga3öovaai3i3209f", Arrays.asList(tags_list[7]),"testpassword_2");
		user[2] = new DataAccessUser("3pf9OEIJfölkfödsiföIÖEg", Arrays.asList(tags_list[1]),"testpassword_3");
		user[3] = new DataAccessUser("(OÖIUDEOdfkös3jsdöIfFes", Arrays.asList(tags_list[2]),"testpassword_4");
		user[4] = new DataAccessUser("234ögaöIi4tjregAGfdj3k2", Arrays.asList(tags_list[0]),"testpassword_5");
		user[5] = new DataAccessUser("P()POI3oi2pidpfbu8sn4(E", Arrays.asList(tags_list[9]),"testpassword_6");
		user[6] = new DataAccessUser(":DFLÜP§'*FÜi3jöksdf)(§h", Arrays.asList(tags_list[8]),"testpassword_7");
		user[7] = new DataAccessUser("PIPoi4tarTGASFBUg94gwkj", Arrays.asList(tags_list[4]),"testpassword_8");
		user[8] = new DataAccessUser("fapogiG0ß4tGOS)3lsdflog", Arrays.asList(tags_list[5]),"testpassword_9");
		user[9] = new DataAccessUser("'D)§ÖÖJK!(($=GJSKölkdsö", Arrays.asList(tags_list[6]),"testpassword_0");
		
		event[0] = new DataAccessEvent("event_1", "short_info_1", "all_text_1", "http://www.event_1_url.com", Calendar.getInstance());
		event[1] = new DataAccessEvent("event_2", "short_info_2", "all_text_2", "http://www.event_2_url.com", Calendar.getInstance());
		event[2] = new DataAccessEvent("event_3", "short_info_3", "all_text_3", "http://www.event_3_url.com", Calendar.getInstance());
		event[3] = new DataAccessEvent("event_4", "short_info_4", "all_text_4", "http://www.event_4_url.com", Calendar.getInstance());
		event[4] = new DataAccessEvent("event_5", "short_info_5", "all_text_5", "http://www.event_5_url.com", Calendar.getInstance());
		event[5] = new DataAccessEvent("event_6", "short_info_6", "all_text_6", "http://www.event_6_url.com", Calendar.getInstance());
		event[6] = new DataAccessEvent("event_7", "short_info_7", "all_text_7", "http://www.event_7_url.com", Calendar.getInstance());
		event[7] = new DataAccessEvent("event_8", "short_info_8", "all_text_8", "http://www.event_8_url.com", Calendar.getInstance());
		event[8] = new DataAccessEvent("event_9", "short_info_9", "all_text_9", "http://www.event_9_url.com", Calendar.getInstance());
		event[9] = new DataAccessEvent("event_0", "short_info_0", "all_text_0", "http://www.event_0_url.com", Calendar.getInstance());
		
		news[0] = new DataAccessNews("news_1", "short_info_1", "http://www.news_1_url.com", "http://www.news_1_url.com/image_url", Calendar.getInstance());
		news[1] = new DataAccessNews("news_2", "short_info_2", "http://www.news_2_url.com", "http://www.news_2_url.com/image_url", Calendar.getInstance());
		news[2] = new DataAccessNews("news_3", "short_info_3", "http://www.news_3_url.com", "http://www.news_3_url.com/image_url", Calendar.getInstance());
		news[3] = new DataAccessNews("news_4", "short_info_4", "http://www.news_4_url.com", "http://www.news_4_url.com/image_url", Calendar.getInstance());
		news[4] = new DataAccessNews("news_5", "short_info_5", "http://www.news_5_url.com", "http://www.news_5_url.com/image_url", Calendar.getInstance());
		news[5] = new DataAccessNews("news_6", "short_info_6", "http://www.news_6_url.com", "http://www.news_6_url.com/image_url", Calendar.getInstance());
		news[6] = new DataAccessNews("news_7", "short_info_7", "http://www.news_7_url.com", "http://www.news_7_url.com/image_url", Calendar.getInstance());
		news[7] = new DataAccessNews("news_8", "short_info_8", "http://www.news_8_url.com", "http://www.news_8_url.com/image_url", Calendar.getInstance());
		news[8] = new DataAccessNews("news_9", "short_info_9", "http://www.news_9_url.com", "http://www.news_9_url.com/image_url", Calendar.getInstance());
		news[9] = new DataAccessNews("news_0", "short_info_0", "http://www.news_0_url.com", "http://www.news_0_url.com/image_url", Calendar.getInstance());
		
		job[0] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.ADMINISTRATION, DataAccessTag.DATA_ADMINISTRATION}), "job_1", "short_info_1", "http://www.job_1_url.com");
		job[1] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.CLERK}), "job_2", "short_info_2", "http://www.job_2_url.com");
		job[2] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.DOCTORAND, DataAccessTag.STUDENT_RESEARCH_PROJECT}), "job_3", "short_info_3", "http://www.job_3_url.com");
		job[3] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.ENGINEER}), "job_4", "short_info_4", "http://www.job_4_url.com");
		job[4] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.PROFESSOR, DataAccessTag.SCIENTIST}), "job_5", "short_info_5", "http://www.job_5_url.com");
		job[5] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.GRADUAND}), "job_6", "short_info_6", "http://www.job_6_url.com");
		job[6] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.INDUSTRIAL, DataAccessTag.SALES_OCCUPATION}), "job_7", "short_info_7", "http://www.job_7_url.com");
		job[7] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.OTHERS}), "job_8", "short_info_8", "http://www.job_8_url.com");
		job[8] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.TECHNICAL_EMPLOYEE}), "job_9", "short_info_9", "http://www.job_9_url.com");
		job[9] = new DataAccessJob(Arrays.asList(new DataAccessTag[]{DataAccessTag.THRESHOLD_WORKER, DataAccessTag.TRAINEE}), "job_0", "short_info_0", "http://www.job_0_url.com");
	}

	@Test
	public void testSaveUser() {
		EntityManager em = DbHandlerService.getEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM DataAccessUser").executeUpdate();
		em.getTransaction().commit();
		em.close();
		DbHandlerService.saveUser(user[0]);
		DbHandlerService.saveUser(user[1]);
		DbHandlerService.saveUser(user[2]);
		DbHandlerService.saveUser(user[3]);
		DbHandlerService.saveUser(user[4]);
		DbHandlerService.saveUser(user[5]);
		DbHandlerService.saveUser(user[6]);
		DbHandlerService.saveUser(user[7]);
		DbHandlerService.saveUser(user[8]);
		DbHandlerService.saveUser(user[9]);
		Assert.assertEquals(true, DbHandlerService.getAllUsers().size() == user.length);
		
		user[0].setClientId("f39apb3lad210gek55kj3%3");
		user[3].setPassword("new_password_4");
		DbHandlerService.saveUser(user[0]);
		DbHandlerService.saveUser(user[3]);
		Assert.assertEquals(true, DbHandlerService.getAllUsers().size() == user.length);
	}

	@Test
	public void testDeleteUser() {
		DbHandlerService.deleteUser(user[1].getClientId());
		DbHandlerService.deleteUser(user[4].getClientId());
		DbHandlerService.deleteUser(user[9].getClientId());
		Assert.assertEquals(7, DbHandlerService.getAllUsers().size());
		
	}

	@Test
	public void testGetUser() {
		testSaveUser();
		Assert.assertEquals(true, user[0].equals(DbHandlerService.getUser(user[0].getClientId())));
		Assert.assertEquals(true, user[2].equals(DbHandlerService.getUser(user[2].getClientId())));
		Assert.assertEquals(true, user[3].equals(DbHandlerService.getUser(user[3].getClientId())));
		Assert.assertEquals(true, user[5].equals(DbHandlerService.getUser(user[5].getClientId())));
		Assert.assertEquals(true, user[6].equals(DbHandlerService.getUser(user[6].getClientId())));
		Assert.assertEquals(true, user[7].equals(DbHandlerService.getUser(user[7].getClientId())));
		Assert.assertEquals(true, user[8].equals(DbHandlerService.getUser(user[8].getClientId())));
	}

	@Test
	public void testGetAllUsers() {
		testSaveUser();
		List<DataAccessUser> users = DbHandlerService.getAllUsers();
		
		Assert.assertEquals(user[0].getClientId(), users.get(0).getClientId());
		Assert.assertEquals(user[1].getClientId(), users.get(1).getClientId());
		Assert.assertEquals(user[2].getClientId(), users.get(2).getClientId());
		Assert.assertEquals(user[3].getClientId(), users.get(3).getClientId());
		Assert.assertEquals(user[4].getClientId(), users.get(4).getClientId());
		Assert.assertEquals(user[5].getClientId(), users.get(5).getClientId());
		Assert.assertEquals(user[6].getClientId(), users.get(6).getClientId());
		Assert.assertEquals(user[7].getClientId(), users.get(7).getClientId());
		Assert.assertEquals(user[8].getClientId(), users.get(8).getClientId());
		Assert.assertEquals(user[9 ].getClientId(), users.get(9).getClientId());
	}

	@Test
	public void testGetUsersByTag() {
		testSaveUser();
		List<DataAccessUser> users = DbHandlerService.getUsersByTag(DataAccessTag.DATA_ADMINISTRATION);
		Assert.assertEquals(2, users.size());
		Assert.assertEquals(user[1].getClientId(), users.get(0).getClientId());
		Assert.assertEquals(user[4].getClientId(), users.get(1).getClientId());
		
		users = DbHandlerService.getUsersByTag(DataAccessTag.TECHNICAL_EMPLOYEE);
		Assert.assertEquals(3, users.size());
		Assert.assertEquals(user[2].getClientId(), users.get(0).getClientId());
		Assert.assertEquals(user[5].getClientId(), users.get(1).getClientId());
		Assert.assertEquals(user[7].getClientId(), users.get(2).getClientId());
	}

	@Test
	public void testSaveNews() {
	}

	@Test
	public void testGetLatestNews() {
	}

	@Test
	public void testGetPreviousNews() {
	}

	@Test
	public void testGetAllNews() {
	}

	@Test
	public void testSaveEvent() {
	}

	@Test
	public void testDeleteEvent() {
	}

	@Test
	public void testGetLatestEvents() {
	}

	@Test
	public void testGetPreviousEvents() {
	}

	@Test
	public void testGetAllEvents() {
	}

	@Test
	public void testSaveJob() {
	}

	@Test
	public void testGetLatestJobs() {
	}

	@Test
	public void testGetPreviousJobs() {
	}

	@Test
	public void testGetAllJobs() {
	}

	@Test
	public void testGetJobsByTag() {
	}
	/**/

}
