package edu.kit.isco.KitAlumniApp.server.dataobject;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class that represents a news object
 * @author Alexander Mueller
 *
 */
@XmlRootElement
@Entity
@Table(name = "news")
public class DataAccessNews {
	
	/**
	 * The database id of a news object
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	/**
	 * The title of a news
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * The short description of a news
	 */
	@Column(name = "short_info", length = 500)
	private String shortInfo;

	/**
	 * The link of a news, where it was posted
	 */
	@Column(name = "url", length = 1000)
	private String url;
	
	/**
	 * The link to the news image
	 */
	@Column(name = "image_url", length = 1000)
	private String imageUrl;
	
	/**
	 * The news date
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Calendar date;
	
	/**
	 * Empty constructor required for hibernate
	 */
	public DataAccessNews(){}
	
	/**
	 * Creates a new news object with a title, a short description, a full description, a url, a image url and a date
	 * @param title the news title
	 * @param shortInfo the news short description
	 * @param allText the news full description
	 * @param url the news link
	 * @param imageUrl the news image link
	 * @param date the news date
	 */
	public DataAccessNews(String title, String shortInfo, String url, String imageUrl, Calendar date) {
		this.title = title;
		this.shortInfo = shortInfo;
		this.url = url;
		this.imageUrl = imageUrl;
		this.date = date;
	}
	/**
	 * Returns the news database id
	 * @return the news database id
	 */
	public long getId() {return Id;}
	
	/**
	 * Returns the news title
	 * @return the news title
	 */
	public String getTitle() {return title;}
	
	/**
	 * Returns the news short description
	 * @return the news short description
	 */
	public String getShortInfo() {return shortInfo;}
	
	/**
	 * Returns the news link
	 * @return the news link
	 */
	public String getUrl() {return url;}
	
	/**
	 * Returns the news image url
	 * @return the news image url
	 */
	public String getImageUrl() {return imageUrl;}
	
	/**
	 * Returns the news date
	 * @return the news date
	 */
	public Calendar getDate() {return date;}
	
	/**
	 * Sets the news database id
	 * @param id the new database id
	 */
	public void setId(long id) {Id = id;}
	
	/**
	 * Sets the news title
	 * @param title the new title
	 */
	public void setTitle(String title) {this.title = title;}
	
	/**
	 * Sets the news short description
	 * @param shortInfo the new short description
	 */
	public void setShortInfo(String shortInfo) {this.shortInfo = shortInfo;}
	
	/**
	 * Sets the news url
	 * @param url the new url
	 */
	public void setUrl(String url) {this.url = url;}
	
	/**
	 * Sets the news image url
	 * @param imageUrl the new image url
	 */
	public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
	
	/**
	 * Sets the news date
	 * @param date the new date
	 */
	public void setDate(Calendar date) {this.date = date;}	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuffer("ID : ").append(this.Id)
						.append(" Title : ").append(this.title)
						.append(" Short Info : ").append(this.shortInfo)
						.append(" URL : ").append(this.url)
						.append(" Image Url : ").append(this.imageUrl)
						.append(" Date : ").append(this.date.toString())
						.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DataAccessNews other = (DataAccessNews) obj;
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!url.equals(other.url)) {
			return false;
		}
		return true;
	}
	
	
}
