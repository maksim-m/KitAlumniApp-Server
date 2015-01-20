package edu.kit.isco.KitAlumniApp.server.dataobject;

import java.util.List;

public class DataAccessUser implements DataAcceessObject {
	private long id;
	private String clientId;
	private List<DataAccessTag> tags;
	private String password;
	
	public DataAccessUser(String clientId, String password) {
		super();
		this.clientId = clientId;
		this.password = password;
	}

	public DataAccessUser(String clientId, List<DataAccessTag> tags,
			String password) {
		super();
		this.clientId = clientId;
		this.tags = tags;
		this.password = password;
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
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the tags
	 */
	public List<DataAccessTag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<DataAccessTag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
