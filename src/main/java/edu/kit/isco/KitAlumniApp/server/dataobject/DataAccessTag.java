package edu.kit.isco.KitAlumniApp.server.dataobject;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "tag")
public class DataAccessTag implements DataAccessObject {
	
	public static final DataAccessTag INFORMATICS 			 = new DataAccessTag("Informatics");
	public static final DataAccessTag CHEMISTRY 			 = new DataAccessTag("Chemistry");
	public static final DataAccessTag BIOLOGY				 = new DataAccessTag("Biology");
	public static final DataAccessTag MATHEMATICS	 		 = new DataAccessTag("Mathematics");
	public static final DataAccessTag ELECTRICAL_ENGINEERING = new DataAccessTag("Electrical Engineering");
	public static final DataAccessTag ENGINEERING			 = new DataAccessTag("Engineering");
	public static final DataAccessTag PHYSICS				 = new DataAccessTag("Physics");
	public static final DataAccessTag PROCESS_ENGINEERING	 = new DataAccessTag("Process Engineering");
	public static final DataAccessTag MEDICINE 				 = new DataAccessTag("Medicine");
	public static final DataAccessTag ECONOMICS				 = new DataAccessTag("Economics");
	public static final DataAccessTag LAW					 = new DataAccessTag("Law");
	public static final DataAccessTag MECHANICAL_ENGINEERING = new DataAccessTag("Mechanical Engineering");
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	@Column(name = "name")
	/**
	 * Name der Jobkategorie, beispielsweise Informatik oder Mathematik.
	 */
	private String name;

	public DataAccessTag() {}
	public DataAccessTag(String name) { this.name = name; }
	
	public long getId() { return this.Id; }
	public void setId(long Id) { this.Id = Id; }
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	@Override
	public String toString() {
		return new StringBuffer("ID : ").append(this.Id)
						.append(" Name : ").append(this.name)
						.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return prime * result + ((name == null) ? 0 : name.hashCode());
	}
	
}
