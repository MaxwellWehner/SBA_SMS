package jpa.mainrunner;

import java.util.List;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {
	public static void main(String[] args) {
		StudentService ss = new StudentService();
		ss.registerStudentToCourse("cjaulme9@bing.com", 7);

		// System.out.println(ss.getStudentCourses("cjaulme9@bing.com"));

	}
}
