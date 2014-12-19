package edu.kit.isco.KitAlumniApp.server.datastructures;

/**
 * 
 * @version 0.1
 */
public abstract class ListViewItem {
	private long id;
	private String title;
	private String shortDescription;
	private String fullText;
	
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
	
	
}
