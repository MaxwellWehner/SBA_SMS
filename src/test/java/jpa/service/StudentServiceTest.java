package jpa.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StudentServiceTest {
	StudentService ss;

	@BeforeEach
	void init() {
		ss = new StudentService();
	}

	@Nested
	class ValidateStudentTests {
		@Test
		@DisplayName("correct email and password")
		void testValidateStudentOne() {
			boolean actual = ss.validateStudent("tattwool4@biglobe.ne.jp", "Hjt0SoVmuBz");
			assertTrue(actual);
		}

		@Test
		@DisplayName("wrong email and correct password")
		void testValidateStudentTwo() {
			boolean actual = ss.validateStudent("tattwool4@bigl", "Hjt0SoVmuBz");
			assertFalse(actual);
		}

		@Test
		@DisplayName("wrong email and wrong password")
		void testValidateStudentThree() {
			boolean actual = ss.validateStudent("tattwoo", "Hjt0");
			assertFalse(actual);
		}

		@Test
		@DisplayName("correct email and wrong password")
		void testValidateStudentFour() {
			boolean actual = ss.validateStudent("tattwool4@biglobe.ne.jp", "Hjt0So");
			assertFalse(actual);
		}
	}

}
