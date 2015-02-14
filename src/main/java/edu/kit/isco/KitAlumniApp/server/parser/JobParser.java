package edu.kit.isco.KitAlumniApp.server.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessJob;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;

public class JobParser implements Parser<DataAccessJob> {

	/* (non-Javadoc)
	 * @see edu.kit.isco.KitAlumniApp.server.parser.HtmlParser#init()
	 */
	private String siteUrl = "http://stellen.jobs.kit.edu/cgi-bin/appl/list.pl?action=search";
	private ArrayList<DataAccessJob> jobList;
	private Document doc = null;
		
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
	 * @see edu.kit.isco.KitAlumniApp.server.parser.HtmlParser#parseContent()
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
			
			Pattern pa = Pattern.compile("/cgi-bin/appl/list.pl[^\']*");
			Matcher ma = pa.matcher(href);
			
			while (ma.find()) {
				href = ma.group();
			}
			try {
				URL url =  new URL("http://stellen.jobs.kit.edu" + href);
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = in.readLine()) != null) {
					result.append(line);
				}
				in.close();
				System.out.println(href);
				job = new DataAccessJob(null, title, shortInfo, result.toString(), url.getPath(), null);
				jobList.add(job);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		System.out.println(jobList.size());
		return jobList;
	}

	

}
