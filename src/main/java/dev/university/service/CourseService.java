package dev.university.service;

import dev.university.dao.CourseDAO;
import dev.university.entity.Course;

import java.util.List;

public class CourseService {

    private final CourseDAO courseDAO;

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public List<Course> getCoursesByDepartment(String deptName) {
        return courseDAO.findByDepartmentName(deptName);
    }
}