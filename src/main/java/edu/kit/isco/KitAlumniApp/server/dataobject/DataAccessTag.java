package edu.kit.isco.KitAlumniApp.server.dataobject;

public class DataAccessTag {
	
	public static final DataAccessTag INFORMATICS = new DataAccessTag("Informatics");
	public static final DataAccessTag CHEMISTRY = new DataAccessTag("Chemistry");
	public static final DataAccessTag BIOLOGY = new DataAccessTag("Biology");
	public static final DataAccessTag MATHEMATICS = new DataAccessTag("Mathematics");
	public static final DataAccessTag ELECTRICAL_ENGINEERING = new DataAccessTag("Electrical engineering");
	public static final DataAccessTag ENGINEERING = new DataAccessTag("Engineering");
	public static final DataAccessTag PHYSICS = new DataAccessTag("Physics");
	public static final DataAccessTag PROCESS_ENGINEERING = new DataAccessTag("Process engineering");
	public static final DataAccessTag MEDICINE = new DataAccessTag("Medicine");
	public static final DataAccessTag ECONOMICS = new DataAccessTag("Economics");
	public static final DataAccessTag LAW = new DataAccessTag("Law");
	public static final DataAccessTag MECHANICAL_ENGINEERING = new DataAccessTag("Mechanical engineering");	
	
	private long id;
	private String name;
	
	public DataAccessTag(String name) {
		super();
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
