package jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {

	public List<Student> getAllStudents() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		String hql = "FROM Student";
		Query query = session.createQuery(hql);
		List<Student> results = query.getResultList();
		List<Student> res = results;
		session.close();
		factory.close();
		return results;
	}

	public Student getStudentByEmail(String sEmail) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Student s = session.load(Student.class, sEmail);
		Student ans = new Student(s.getsEmail(), s.getsName(), s.getsPass(), s.getsCourses());
		session.close();
		factory.close();
		return ans;
	}

	public boolean validateStudent(String sEmail, String sPassword) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		String hql = "FROM Student s WHERE s.sEmail = :email AND s.sPass = :pass";
		TypedQuery query = session.createQuery(hql);
		query.setParameter("email", sEmail);
		query.setParameter("pass", sPassword);
		List<Student> result = query.getResultList();
		session.close();
		factory.close();
		return result.size() > 0;
	}

	public void registerStudentToCourse(String sEmail, int cId) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Course c = session.load(Course.class, cId);
		Student currStudent = session.load(Student.class, sEmail);
		Set<Course> tempCourse = currStudent.getsCourses();
		tempCourse.add(c);
		Student newS = new Student();
		newS.setsEmail(currStudent.getsEmail());
		newS.setsName(currStudent.getsName());
		newS.setsPass(currStudent.getsPass());
		newS.setsCourses(tempCourse);

		session.merge(newS);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}

	public List<Course> getStudentCourses(String sEmail) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Student s = session.load(Student.class, sEmail);
		List<Course> lc = new ArrayList<Course>();
		lc.addAll(s.getsCourses());
		return lc;
	}

}
