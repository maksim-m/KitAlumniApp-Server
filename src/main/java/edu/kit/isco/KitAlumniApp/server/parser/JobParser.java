package edu.kit.isco.KitAlumniApp.server.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessTag;

/**
 * @author Moony
 *
 */
public class JobParser implements Parser<DataAccessJob> {

	/**
	 * The site url where the jobs are parsed from
	 */
	private String siteUrl = "http://stellen.jobs.kit.edu/cgi-bin/appl/list.pl?action=search";
	
	/**
	 * Container for parsed jobs
	 */
	private ArrayList<DataAccessJob> jobList;
	
	/**
	 * the HTML-Document
	 */
	private Document doc = null;
		
	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.parser.Parser#init()
	 */
	public void init() {
		jobList = new ArrayList<DataAccessJob>();
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(siteUrl);
		post.setHeader("Content-Type", "text/html; charset=utf-8");
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("loc_nr",
				"12B87EE0-C700-11D4-8972-0050BAC69B70"));
		urlParameters.add(new BasicNameValuePair("cat_nr", ""));
		urlParameters.add(new BasicNameValuePair("search", ""));
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = client.execute(post);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
			doc = Jsoup.parse(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.parser.Parser#parseContent()
	 */
	public ArrayList<DataAccessJob> parseContent() {
		DataAccessJob job;
		Element table = doc.getElementById("search_results");
		table = table.select("tbody").first();
		for (Element e : table.select("tr")) {
			
			Element td = e.select("td").get(1);
			String shortInfo = td.text();
			
			td = e.select("td").get(2);
			Element a = td.select("a[href]").first();
			String title = a.text();
			String href = a.attr("href");
			td = e.select("td").get(3);
			ArrayList<DataAccessTag> tags = new ArrayList<DataAccessTag>();
			tags.add(StringToTag(td.text()));
			
			Pattern pa = Pattern.compile("/cgi-bin/appl/list.pl[^\']*");
			Matcher ma = pa.matcher(href);
			
			while (ma.find()) {
				href = ma.group();
			}
			job = new DataAccessJob(tags, title, shortInfo, "http://stellen.jobs.kit.edu" + href);
			jobList.add(job);
		}
		return jobList;
	}
	
	
	/**
	 * Converts a given parsed text that represents a job category to its respective tag object.
	 * @param text the parsed text representation of a job category
	 * @return the respective tag object
	 */
	private DataAccessTag StringToTag(String text) {
		DataAccessTag tag = null;
		switch (text) {
			case "Diplomanden/innen":
				tag = DataAccessTag.GRADUAND;
				break;
			case "Angestellte in der Datenverarbeitung":
				tag = DataAccessTag.DATA_ADMINISTRATION;
				break;
			case "Angestellte in der Datenverarbeitung (m/w)":
				tag = DataAccessTag.DATA_ADMINISTRATION;
				break;
			case "Auszubildende":
				tag = DataAccessTag.TRAINEE;
				break;
			case "Beamter/in (A13/A14/A15)":
				tag = DataAccessTag.CLERK;
				break;
			case "Doktoranden/innen":
				tag = DataAccessTag.DOCTORAND;
				break;
			case "FH/BA-Ingenieure/innen":
				tag = DataAccessTag.ENGINEER;
				break;
			case "Gewerbliche Mitarbeiter/innen":
				tag = DataAccessTag.INDUSTRIAL;
				break;
			case "Kaufm&#228;nnische Mitarbeiter/innen":
				tag = DataAccessTag.SALES_OCCUPATION;
				break;
			case "Praktikanten/innen":
				tag = DataAccessTag.THRESHOLD_WORKER;
				break;
			case "Professoren/innen (W3/W2/W1)":
				tag = DataAccessTag.PROFESSOR;
				break;
			case "Studienarbeiten":
				tag = DataAccessTag.STUDENT_RESEARCH_PROJECT;
				break;
			case "Technische Mitarbeiter/innen":
				tag = DataAccessTag.TECHNICAL_EMPLOYEE;
				break;
			case "Verwaltung":
				tag = DataAccessTag.ADMINISTRATION;
				break;
			case "Wissenschaftler/Ingenieure/innen (Uni/TH/TU)":
				tag = DataAccessTag.SCIENTIST;
				break;
			default:
				tag = DataAccessTag.OTHERS;
				break;
		}
		
		return tag;
	}

	

}
