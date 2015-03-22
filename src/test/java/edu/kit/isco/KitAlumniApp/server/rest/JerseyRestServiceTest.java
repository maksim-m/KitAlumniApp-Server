package edu.kit.isco.KitAlumniApp.server.rest;

import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class JerseyRestServiceTest {
	
	@Before
    public void setUp(){
        //RestAssured.basePath = "http://localhost:8080/KitAlumniApp-Server/rest/service/";
    }
	
	@Test
    public void testResponseCode() {
        expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("http://localhost:8080/KitAlumniApp-Server/rest/service/news/latest");
        expect().statusCode(200).contentType(ContentType.JSON).when()
        		.get("http://localhost:8080/KitAlumniApp-Server/rest/service/events");
        expect().statusCode(200).contentType(ContentType.JSON).when()
        		.get("http://localhost:8080/KitAlumniApp-Server/rest/service/jobs/latest");
    }
}
