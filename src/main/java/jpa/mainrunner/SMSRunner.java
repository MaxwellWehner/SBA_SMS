package jpa.mainrunner;

import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {
	public static void main(String[] args) {
		int ans = 0;
		Scanner mainInput = new Scanner(System.in);
		while (ans != 2) {

			System.out.format("Are you a(n)\n\n1. Student\n2. Quit Application\n\nPlease Enter 1 or 2.\n");
			ans = mainInput.nextInt();
			switch (ans) {
			case 1:
				String outMessage = beginLogin();
				System.out.println(outMessage);
				ans = 2;
				break;
			case 2:
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Incorrect input");
				break;
			}

		}
		mainInput.close();
	}

	public static String beginLogin() {
		StudentService ss = new StudentService();
		CourseService cs = new CourseService();
		Scanner input = new Scanner(System.in);
		out.print("Enter your email address: ");
		String email = input.next();
		out.print("Enter your password: ");
		String password = input.next();
		if (ss.validateStudent(email, password)) {
			List<Course> currStudentCourses = ss.getStudentCourses(email);
			System.out.println("My Classes:");
			System.out.format("%-10s %-20s %-20s\n", "#", "COURSE NAME", "INSTRUCTOR NAME");
			for (Course c : currStudentCourses) {
				System.out.format("%-10d %-20s %-20s\n", c.getcId(), c.getcName(), c.getcInstructorName());
			}
			int secAns = 0;
			String retMessage = "";
			while (secAns != 2) {
				System.out.format("\n1. Register to Class\n2. Logout\n");
				secAns = input.nextInt();
				switch (secAns) {
				case 1:
					List<Course> allCourses = cs.getAllCourses();
					System.out.println("All Courses:");
					System.out.format("%-10s %-20s %-20s\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
					for (Course c : allCourses) {
						System.out.format("%-10d %-20s %-20s\n", c.getcId(), c.getcName(), c.getcInstructorName());
					}
					System.out.println("\nWhich Course?\n");
					int thirdAns = input.nextInt();
					ss.registerStudentToCourse(email, thirdAns);
					currStudentCourses = ss.getStudentCourses(email);
					System.out.println("My Classes:");
					System.out.format("%-10s %-20s %-20s\n", "#", "COURSE NAME", "INSTRUCTOR NAME");
					for (Course c : currStudentCourses) {
						System.out.format("%-10d %-20s %-20s\n", c.getcId(), c.getcName(), c.getcInstructorName());
					}
					System.out.println();
					secAns = 2;
					retMessage = "You have been signed out.";
					break;
				case 2:
					retMessage = "You have been signed out.";
					break;
				default:
					System.out.println("Incorrect input");
					break;
				}
			}
			return retMessage;
		} else {
			return "Email or password were incorrect.";
		}
	}
}
