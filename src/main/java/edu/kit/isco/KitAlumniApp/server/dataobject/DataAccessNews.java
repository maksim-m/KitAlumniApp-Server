package edu.kit.isco.KitAlumniApp.server.dataobject;

import java.util.Calendar;

/**
 * News class.
 * @version 0.1
 *
 */
public class DataAccessNews implements DataAcceessObject {
	private long id;
	private String title;
	private String shortDescription;
	private String fullText;
	private String url;
	private String imageUrl = null;
	private Calendar date;
	
	public DataAccessNews(String title, String shortDescription, String url) {
		super();
		this.title = title;
		this.shortDescription = shortDescription;
		this.url = url;
	}

	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}



	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}



	/**
	 * @return the fullText
	 */
	public String getFullText() {
		return fullText;
	}



	/**
	 * @param fullText the fullText to set
	 */
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}



	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}



	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}



	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}



	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuffer(" ID : ").append(this.getId())
				.append(" Title : ").append(this.getTitle())
				.append(" Short Description: ").append(this.getShortDescription())
				.append(" Full Text : ").append(this.getFullText())
				.append(" URL : ").append(this.getUrl())
				.append(" imageURL : ").append(this.getImageUrl())
				.toString();
	}
	
	
	
	
}
