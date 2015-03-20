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
 * The class that represents a Event
 * @author Alexander Mueller
 *
 */
@XmlRootElement
@Entity
@Table(name = "event")
public class DataAccessEvent implements DataAccessObject {

	/**
	 * The database id of an event object
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	/**
	 * The title of an event
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * A short description of an event
	 */
	@Column(name = "short_info", length = 500)
	private String shortInfo;
	
	/**
	 * An overall description of an event
	 */
	@Column(name = "all_text", length = 10000)
	private String allText;

	/**
	 * A link to the event, where it was posted
	 */
	@Column(name = "url", length = 1000)
	private String url;
	
	/**
	 * The events date
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Calendar date;
	
	/**
	 * Empty constructor required for hibernate
	 */
	public DataAccessEvent() {}
	
	/**
	 * Creates an event object with a title, a short description, a long description, a url and a date
	 * @param title the events title
	 * @param shortInfo the events short description
	 * @param allText the events full text
	 * @param url the events link
	 * @param date the events date
	 */
	public DataAccessEvent(String title, String shortInfo, String allText, String url, Calendar date) {
		this.title = title;
		this.shortInfo = shortInfo;
		this.allText = allText;
		this.url = url;
		this.date = date;
	}
	
	/**
	 * Returns the events database id
	 * @return the events database id
	 */
	public long getId() {return Id;}
	
	/**
	 * Returns the events title
	 * @return the events title
	 */
	public String getTitle() {return title;}
	
	/**
	 * Returns the events short description
	 * @return the events short description
	 */
	public String getShortInfo() {return shortInfo;}
	
	/**
	 * Returns the events full description
	 * @return the events full description
	 */
	public String getAllText() {return allText;}
	
	/**
	 * Returns the events link
	 * @return the events link
	 */
	public String getUrl() {return url;}
	
	/**
	 * Returns the events date
	 * @return the events date
	 */
	public Calendar getDate() {return date;}
	
	/**
	 * Sets the events database id
	 * @param id the new database id
	 */
	public void setId(long id) {Id = id;}
	
	/**
	 * Sets the events title
	 * @param title the new title
	 */
	public void setTitle(String title) {this.title = title;}
	
	/**
	 * Sets the events short description
	 * @param shortInfo the new short description
	 */
	public void setShortInfo(String shortInfo) {this.shortInfo = shortInfo;}
	
	/**
	 * Sets the events full description
	 * @param allText the new full description
	 */
	public void setAllText(String allText) {this.allText = allText;}
	
	/**
	 * Sets the events link
	 * @param url the new link
	 */
	public void setUrl(String url) {this.url = url;}
	
	/**
	 * Sets the events date
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
						.append(" All Text : ").append(this.allText)
						.append(" URL : ").append(this.url)
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
		DataAccessEvent other = (DataAccessEvent) obj;
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
