package jpa.mainrunner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

//class to setup tables initally
//requires database to be pre-created with name sms
//DO NOT use sql files for set up
//this will add all given test data 
public class TableSetUp {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Course c1 = new Course(1, "English", "Anderea Scamaden");
		Course c2 = new Course(2, "Mathematics", "Eustace Niemetz");
		Course c3 = new Course(3, "Anatomy", "Reynolds Pastor");
		Course c4 = new Course(4, "Organic Chemistry", "Odessa Belcher");
		Course c5 = new Course(5, "Physics", "Dani Swallow");
		Course c6 = new Course(6, "Digital Logic", "Glenden Reilingen");
		Course c7 = new Course(7, "Object Oriented Programming", "Giselle Ardy");
		Course c8 = new Course(8, "Data Structures", "Carolan Stoller");
		Course c9 = new Course(9, "Politics", "Carmita De Maine");
		Course c10 = new Course(10, "Art", "Kingsly Doxsey");
		session.save(c1);
		session.save(c2);
		session.save(c3);
		session.save(c4);
		session.save(c5);
		session.save(c6);
		session.save(c7);
		session.save(c8);
		session.save(c9);
		session.save(c10);
		Student s1 = new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj");
		Student s2 = new Student("sbowden1@yellowbook.com", "Sonnnie Bowden", "SJc4aWSU");
		Student s3 = new Student("qllorens2@howstuffworks.com", "Quillan Llorens", "W6rJuxd");
		Student s4 = new Student("cstartin3@flickr.com", "Clem Startin", "XYHzJ1S");
		Student s5 = new Student("tattwool4@biglobe.ne.jp", "Thornie Attwool", "Hjt0SoVmuBz");
		Student s6 = new Student("hguerre5@deviantart.com", "Harcourt Guerre", "OzcxzD1PGs");
		Student s7 = new Student("htaffley6@columbia.edu", "Holmes Taffley", "xowtOQ");
		Student s8 = new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j");
		Student s9 = new Student("ljiroudek8@sitemeter.com", "Laryssa Jiroudek", "bXRoLUP");
		Student s10 = new Student("cjaulme9@bing.com", "Cahra Jaulme", "FnVklVgC6r6");
		session.save(s1);
		session.save(s2);
		session.save(s3);
		session.save(s4);
		session.save(s5);
		session.save(s6);
		session.save(s7);
		session.save(s8);
		session.save(s9);
		session.save(s10);
		t.commit();
		System.out.println("successfully created tables");
		factory.close();
		session.close();
	}
}
