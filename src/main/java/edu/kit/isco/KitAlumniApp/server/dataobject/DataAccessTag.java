package edu.kit.isco.KitAlumniApp.server.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * A class that represents the job categories
 * @author Alexander Mueller
 *
 */
@XmlRootElement
@Entity
@Table(name = "tag")
public class DataAccessTag implements DataAccessObject {
	
	
	/**
	 * A tag object that represents the 'Employee in Data Administration' Category
	 */
	public static final DataAccessTag DATA_ADMINISTRATION    	= new DataAccessTag("Employee in Data Administration");
	
	/**
	 * A tag object that represents the 'Trainee' Category
	 */
	public static final DataAccessTag TRAINEE 			 		= new DataAccessTag("Trainee");
	
	/**
	 * A tag object that represents the 'Clerk' Category
	 */
	public static final DataAccessTag CLERK				 		= new DataAccessTag("Clerk");
	
	/**
	 * A tag object that represents the 'Graduand' Category
	 */
	public static final DataAccessTag GRADUAND	 		 		= new DataAccessTag("Graduand");
	
	/**
	 * A tag object that represents the 'Doctorand' Category
	 */
	public static final DataAccessTag DOCTORAND 		 		= new DataAccessTag("Doctorand");
	
	/**
	 * A tag object that represents the 'FH/BA Engineer' Category
	 */
	public static final DataAccessTag ENGINEER			 		= new DataAccessTag("FH/BA Engineer");
	
	/**
	 * A tag object that represents the 'Industrial Job' Category
	 */
	public static final DataAccessTag INDUSTRIAL				= new DataAccessTag("Industrial Job");
	
	/**
	 * A tag object that represents the 'Sales Occupation' Category
	 */
	public static final DataAccessTag SALES_OCCUPATION	 		= new DataAccessTag("Sales Occupation");
	
	/**
	 * A tag object that represents the 'Threshold Worker' Category
	 */
	public static final DataAccessTag THRESHOLD_WORKER 			= new DataAccessTag("Threshold Worker");
	
	/**
	 * A tag object that represents the 'Professor' Category
	 */
	public static final DataAccessTag PROFESSOR			 		= new DataAccessTag("Professor");
	
	/**
	 * A tag object that represents the 'Technical Employee' Category
	 */
	public static final DataAccessTag TECHNICAL_EMPLOYEE		= new DataAccessTag("Technical Employee");
	
	/**
	 * A tag object that represents the 'Student Research Project' Category
	 */
	public static final DataAccessTag STUDENT_RESEARCH_PROJECT 	= new DataAccessTag("Student Research Project");
	
	/**
	 * A tag object that represents the 'Administration' Category
	 */
	public static final DataAccessTag ADMINISTRATION 			= new DataAccessTag("Administration");
	
	/**
	 * A tag object that represents the 'Scientist' Category
	 */
	public static final DataAccessTag SCIENTIST 				= new DataAccessTag("Scientist");
	
	/**
	 * A tag object that represents the 'Others' Category
	 */
	public static final DataAccessTag OTHERS 					= new DataAccessTag("Others");
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/**
	 * The database id of a tag object. This value is automatically assigned.
	 */
	private long Id;
	
	@Column(name = "name")
	/**
	 * The name of a job category, for example scientist or professor
	 */
	private String name;

	/**
	 * Empty constructor required for hibernate 
	 */
	public DataAccessTag() {}
	
	/**
	 * Creates a new tag object
	 * @param name the tags name
	 */
	public DataAccessTag(String name) { this.name = name; }
	
	/**
	 * Returns the tag objects database id
	 * @return the database id
	 */
	public long getId() { return this.Id; }
	
	/**
	 * Sets the tags database id
	 * @param Id the new database id
	 */
	public void setId(long Id) { this.Id = Id; }
	
	/**
	 * Returns the tags name
	 * @return the tags name
	 */
	public String getName() { return this.name; }
	
	/**
	 * Sets the tags name
	 * @param name the new name
	 */
	public void setName(String name) { this.name = name; }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuffer("ID : ").append(this.Id)
						.append(" Name : ").append(this.name)
						.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return prime * result + ((name == null) ? 0 : name.hashCode());
	}
	
	/**
	 * Returns a predefined tag object by a given name.
	 * @param name the name of a job category
	 * @return the respective tag object
	 */
	public static DataAccessTag getTagByName(String name) {
		DataAccessTag tag = null;
		switch (name) {
			case "Graduand":
				tag = DataAccessTag.GRADUAND;
				break;
			case "Employee in Data Administration":
				tag = DataAccessTag.DATA_ADMINISTRATION;
				break;
			case "Trainee":
				tag = DataAccessTag.TRAINEE;
				break;
			case "Clerk":
				tag = DataAccessTag.CLERK;
				break;
			case "Doctorand":
				tag = DataAccessTag.DOCTORAND;
				break;
			case "FH/BA Engineer":
				tag = DataAccessTag.ENGINEER;
				break;
			case "Industrial Job":
				tag = DataAccessTag.INDUSTRIAL;
				break;
			case "Sales Occupation":
				tag = DataAccessTag.SALES_OCCUPATION;
				break;
			case "Threshold Worker":
				tag = DataAccessTag.THRESHOLD_WORKER;
				break;
			case "Professor":
				tag = DataAccessTag.PROFESSOR;
				break;
			case "Student Research Project":
				tag = DataAccessTag.STUDENT_RESEARCH_PROJECT;
				break;
			case "Technical Employee":
				tag = DataAccessTag.TECHNICAL_EMPLOYEE;
				break;
			case "Administration":
				tag = DataAccessTag.ADMINISTRATION;
				break;
			case "Scientist":
				tag = DataAccessTag.SCIENTIST;
				break;
			default:
				tag = DataAccessTag.OTHERS;
				break;
		}
		
		return tag;
	}
	
	/**
	 * Converts a given parsed text that represents a job category to its respective tag object.
	 * @param text the parsed text representation of a job category
	 * @return the respective tag object
	 */
	public static DataAccessTag StringToTag(String text) {
		DataAccessTag tag = null;
		switch (text) {
			case "Diplomanden/innen":
				tag = DataAccessTag.GRADUAND;
				break;
			case "Angestellte in der Datenverarbeitung":
				tag = DataAccessTag.DATA_ADMINISTRATION;
				break;
			case "Angestellte in der Datenverarbeitung (m/w)":
				tag = DataAccessTag.DATA_ADMINISTRATION;
				break;
			case "Auszubildende":
				tag = DataAccessTag.TRAINEE;
				break;
			case "Beamter/in (A13/A14/A15)":
				tag = DataAccessTag.CLERK;
				break;
			case "Doktoranden/innen":
				tag = DataAccessTag.DOCTORAND;
				break;
			case "FH/BA-Ingenieure/innen":
				tag = DataAccessTag.ENGINEER;
				break;
			case "Gewerbliche Mitarbeiter/innen":
				tag = DataAccessTag.INDUSTRIAL;
				break;
			case "Kaufm&#228;nnische Mitarbeiter/innen":
				tag = DataAccessTag.SALES_OCCUPATION;
				break;
			case "Praktikanten/innen":
				tag = DataAccessTag.THRESHOLD_WORKER;
				break;
			case "Professoren/innen (W3/W2/W1)":
				tag = DataAccessTag.PROFESSOR;
				break;
			case "Studienarbeiten":
				tag = DataAccessTag.STUDENT_RESEARCH_PROJECT;
				break;
			case "Technische Mitarbeiter/innen":
				tag = DataAccessTag.TECHNICAL_EMPLOYEE;
				break;
			case "Verwaltung":
				tag = DataAccessTag.ADMINISTRATION;
				break;
			case "Wissenschaftler/Ingenieure/innen (Uni/TH/TU)":
				tag = DataAccessTag.SCIENTIST;
				break;
			default:
				tag = DataAccessTag.OTHERS;
				break;
		}
		
		return tag;
	}
	
}
