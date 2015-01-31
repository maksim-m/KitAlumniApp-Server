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
	
	@Column(name = "all_text", length = 10000)
	private String allText;

	@Column(name = "url")
	private String url;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Calendar date;
	
	public DataAccessJob() {};
	public DataAccessJob(List<DataAccessTag> tags, String title, String shortInfo, String allText, String url, Calendar date) {
		this.tags = tags;
		this.title = title;
		this.shortInfo = shortInfo;
		this.allText = allText;
		this.url = url;
		this.date = date;
	}
	
	public long getId() {return Id;}
	public List<DataAccessTag> getTags() {return tags;}
	public String getTitle() {return title;}
	public String getShortInfo() {return shortInfo;}
	public String getAllText() {return allText;}
	public String getUrl() {return url;}
	public Calendar getDate() {return date;}
	public void setId(long id) {Id = id;}
	public void setTags(List<DataAccessTag> tags) {this.tags = tags;}
	public void setTitle(String title) {this.title = title;}
	public void setShortInfo(String shortInfo) {this.shortInfo = shortInfo;}
	public void setAllText(String allText) {this.allText = allText;}
	public void setUrl(String url) {this.url = url;}
	public void setDate(Calendar date) {this.date = date;}
	
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
