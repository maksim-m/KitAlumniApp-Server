package edu.kit.isco.KitAlumniApp.server.dataobject;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "job")
public class DataAccessJob implements DataAccessObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	@ManyToMany
	@JoinTable(name = "job_tag",
	joinColumns={@JoinColumn(name = "job_id", referencedColumnName = "Id")},
	inverseJoinColumns={@JoinColumn(name = "tag_id", referencedColumnName = "Id")})
	private List<DataAccessTag> tags;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "short_info", length = 500)
	private String shortInfo;
	
	@Column(name = "url", length = 1000)
	private String url;
	
	public DataAccessJob() {};
	public DataAccessJob(List<DataAccessTag> tags, String title, String shortInfo, String url) {
		this.tags = tags;
		this.title = title;
		this.shortInfo = shortInfo;
		this.url = url;
	}
	
	public long getId() {return Id;}
	public List<DataAccessTag> getTags() {return tags;}
	public String getTitle() {return title;}
	public String getShortInfo() {return shortInfo;}
	public String getUrl() {return url;}
	public void setId(long id) {Id = id;}
	public void setTags(List<DataAccessTag> tags) {this.tags = tags;}
	public void setTitle(String title) {this.title = title;}
	public void setShortInfo(String shortInfo) {this.shortInfo = shortInfo;}
	public void setUrl(String url) {this.url = url;}
	
	@Override
	public String toString() {
		return new StringBuffer("ID : ").append(this.Id)
					    .append(" Tags : ").append(tags.toString())
						.append(" Title : ").append(this.title)
						.append(" Short Info : ").append(this.shortInfo)
						.append(" URL : ").append(this.url)
						.toString();
	}
	
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
		if (shortInfo == null) {
			if (other.shortInfo != null) {
				return false;
			}
		} else if (!shortInfo.equals(other.shortInfo)) {
			return false;
		}
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
