package jpa.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//course entity for db using hibernate
@Entity
@Table(name = "Course")
public class Course {

	@Id
	@Column(nullable = false, unique = true)
	private int cId;

	@Column(nullable = false, length=50)
	private String cName;

	@Column(nullable = false, length=50)
	private String cInstructorName;

	public Course() {
	}

	public Course(int id, String name, String instructor) {
		this.cId = id;
		this.cName = name;
		this.cInstructorName = instructor;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cName=" + cName + ", cInstructorName=" + cInstructorName + "]";
	}
	
}
