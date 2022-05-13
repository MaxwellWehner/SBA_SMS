package jpa.entitymodels;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {

	@Id
	@Column(nullable = false, unique = true, length=50)
	private String sEmail;

	@Column(nullable = false, length=50)
	private String sName;

	@Column(nullable = false, length=50)
	private String sPass;
	
	//Talked to kevin, he said a set was ok to use instaed of a list
	@ManyToMany(targetEntity = Course.class, cascade=CascadeType.ALL)
	private Set<Course> sCourses;

	public Student() {
		super();
	}
	
	public Student(String email, String name, String password) {
		this.sEmail = email;
		this.sName = name;
		this.sPass = password;
	}

	public Student(String email, String name, String password, Set<Course> sCourses) {
		this.sEmail = email;
		this.sName = name;
		this.sPass = password;
		this.sCourses = sCourses;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public Set<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(Set<Course> sCourses) {
		this.sCourses = sCourses;
	}

	@Override
	public String toString() {
		return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPass=" + sPass + "]";
	}
	
	
}
