package edu.kit.isco.KitAlumniApp.server.dataobject;

/**
 * 
 * @version 0.1
 */
public abstract class ListViewItem {
	private long id;
	private String title;
	private String shortDescription;
	private String fullText;
	private String url;
	
	/**
	 * 
	 * @param title Title
	 * @param shortDescription Short Description
	 * @param url URL
	 */
	public ListViewItem(String title, String shortDescription, String url) {
		this.title = title;
		this.shortDescription = shortDescription;
		this.url = url;
	}
	
	/**
	 * 
	 * @return Id
	 */
	public long getId() {
		return id;
	}
	/**
	 * 
	 * @return Title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @return Short description 
	 */
	public String getShortDescription() {
		return shortDescription;
	}
	/**
	 * 
	 * @return Full text
	 */
	public String getFullText() {
		return fullText;
	}

	/**
	 * 
	 * @param id Id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @param fullText Full Text
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
	
	
	
	
}
