package edu.kit.isco.KitAlumniApp.server.dataobject;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class that represents a job object
 * @author Alexander Mueller
 *
 */
@XmlRootElement
@Entity
@Table(name = "job")
public class DataAccessJob implements DataAccessObject {
	
	/**
	 * The database id of a job object
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	/**
	 * All job categories that fit the job
	 */
	@ManyToMany
	@JoinTable(name = "job_tag",
	joinColumns={@JoinColumn(name = "job_id", referencedColumnName = "Id")},
	inverseJoinColumns={@JoinColumn(name = "tag_id", referencedColumnName = "Id")})
	private List<DataAccessTag> tags;
	
	/**
	 * The title of a job
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * The short description of a job
	 */
	@Column(name = "short_info", length = 500)
	private String shortInfo;
	
	/**
	 * The link of a job, where it was posted
	 */
	@Column(name = "url", length = 1000)
	private String url;
	
	/**
	 * Empty constructor required for hibernate
	 */
	public DataAccessJob() {};
	
	/**
	 * Creates a new job object with a list of tags, a title, a short description and a url
	 * @param tags the list of job categories
	 * @param title the jobs title
	 * @param shortInfo the jobs short description
	 * @param url the jobs url
	 */
	public DataAccessJob(List<DataAccessTag> tags, String title, String shortInfo, String url) {
		this.tags = tags;
		this.title = title;
		this.shortInfo = shortInfo;
		this.url = url;
	}
	
	/**
	 * Returns the jobs database id
	 * @return the jobs database id
	 */
	public long getId() {return Id;}
	
	/**
	 * Returns the list of job categories the jobs to
	 * @return the list of job categories
	 */
	public List<DataAccessTag> getTags() {return tags;}
	
	/**
	 * Returns the jobs title
	 * @return the jobs title
	 */
	public String getTitle() {return title;}
	
	/**
	 * Returns the jobs short description
	 * @return the jobs short description
	 */
	public String getShortInfo() {return shortInfo;}
	
	/**
	 * Returns the jobs link
	 * @return the jobs link
	 */
	public String getUrl() {return url;}
	
	/**
	 * Sets the jobs database id
	 * @param id the new database id
	 */
	public void setId(long id) {Id = id;}
	
	/**
	 * Sets the list of job categories 
	 * @param tags the new list of job categories
	 */
	public void setTags(List<DataAccessTag> tags) {this.tags = tags;}
	
	/**
	 * Sets the jobs title
	 * @param title the new title
	 */
	public void setTitle(String title) {this.title = title;}
	
	/**
	 * Sets the jobs short description
	 * @param shortInfo the new short description
	 */
	public void setShortInfo(String shortInfo) {this.shortInfo = shortInfo;}
	
	/**
	 * Sets the jobs link
	 * @param url the new link
	 */
	public void setUrl(String url) {this.url = url;}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuffer("ID : ").append(this.Id)
					    .append(" Tags : ").append(tags.toString())
						.append(" Title : ").append(this.title)
						.append(" Short Info : ").append(this.shortInfo)
						.append(" URL : ").append(this.url)
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
		DataAccessJob other = (DataAccessJob) obj;
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
