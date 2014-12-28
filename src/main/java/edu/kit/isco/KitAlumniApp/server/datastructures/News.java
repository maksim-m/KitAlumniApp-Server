package edu.kit.isco.KitAlumniApp.server.datastructures;

/**
 * News class.
 * @version 0.1
 *
 */
public class News extends ListViewItem {
	private String imageUrl = null;
	
	/**
	 * 
	 * @param title Title
	 * @param shortDescription Short Description
	 * @param url URL
	 */
	public News(String title, String shortDescription, String url) {
		super(title, shortDescription, url);
		
	}
	
	/**
	 * 
	 * @return URL to news image, when a news has a image, null otherwise
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 
	 * @param imageUrl URL to news image
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
