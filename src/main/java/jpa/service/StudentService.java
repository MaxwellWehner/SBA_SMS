package jpa.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO{

	public List<Student> getAllStudents() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		String hql = "FROM Student";
		Query query = session.createQuery(hql);
		List<Student> results = query.getResultList();
		return results;
	}

	public Student getStudentByEmail(String sEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validateStudent(String sEmail, String sPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	public void registerStudentToCourse(String sEmail, int cId) {
		// TODO Auto-generated method stub
		
	}

	public List<Course> getStudentCourses(String sEmail) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
