package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;


//interface DAO for use in CourseService
public interface CourseDAO {
	public List<Course> getAllCourses();
}
