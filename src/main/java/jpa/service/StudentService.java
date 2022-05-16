package jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {

	//method to get all students from db
	public List<Student> getAllStudents() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		List<Student> res = null;
		//use try catch incase something breaks 
		try {
			//uses hql to get all students and puts it in a list results
			String hql = "FROM Student";
			Query query = session.createQuery(hql);
			List<Student> results = query.getResultList();
			res = results; //assign res to results so closing the session doesn't break it
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		session.close();
		factory.close();
		return res;
	}
	
	//gets a student based on a string email
	public Student getStudentByEmail(String sEmail) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Student ans = null;
		try {
			Student s = session.load(Student.class, sEmail);
			ans = new Student(s.getsEmail(), s.getsName(), s.getsPass(), s.getsCourses());
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		session.close();
		factory.close();
		return ans;
	}
	
	//checks if a student is in the database given the correct email and password
	public boolean validateStudent(String sEmail, String sPassword) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		List<Student> result = new ArrayList<Student>();
		try {
			//uses hql with variable injection to create a sql string with variables
			String hql = "FROM Student s WHERE s.sEmail = :email AND s.sPass = :pass";
			TypedQuery query = session.createQuery(hql);
			query.setParameter("email", sEmail);
			query.setParameter("pass", sPassword);
			result = query.getResultList();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		session.close();
		factory.close();
		//if the size of the returned query list is greater than one then it found a match and the student is validated
		return result.size() > 0;
	}
	
	//regesters a student for a course and prints appropriate message if it cant
	public void registerStudentToCourse(String sEmail, int cId) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Course c = null;
		//trys to find the course they want
		try {
			c = session.load(Course.class, cId);
			System.out.println(c);
		} catch (HibernateException ex) {
			//if the course cant be found
			System.out.println("invalid course number");
			session.close();
			factory.close();
			return;
		}
		//if the course can be found
		try {
			//load the student
			Student currStudent = session.load(Student.class, sEmail);
			Set<Course> tempCourse = currStudent.getsCourses();
			//check if they are already in the course
			if (tempCourse.contains(c)) {
				System.out.println("You are already registered in that course!");
			}
			//update the join table with the new info
			tempCourse.add(c);
			Student newS = new Student();
			newS.setsEmail(currStudent.getsEmail());
			newS.setsName(currStudent.getsName());
			newS.setsPass(currStudent.getsPass());
			newS.setsCourses(tempCourse);
			//megre them back
			session.merge(newS);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		session.close();
		factory.close();
	}
	
	
	//gets all the courses for a student 
	public List<Course> getStudentCourses(String sEmail) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		List<Course> lc = new ArrayList<Course>();
		//we load the student from the db and all the courses will be in that students courses set
		try {
			Student s = session.load(Student.class, sEmail);
			lc.addAll(s.getsCourses());
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return lc;
	}

}
