package dev.university.service;

import dev.university.dao.CourseDAO;
import dev.university.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CourseService {

    private final CourseDAO courseDAO;
    private final EntityManagerFactory emf;

    public CourseService() {
        this.courseDAO = null;
        this.emf = Persistence.createEntityManagerFactory("hello-jpa");
    }

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
        this.emf = null;
    }

    public List<Course> getCoursesByDepartment(String deptName) {
        if (courseDAO != null) {
            return courseDAO.findByDepartmentName(deptName);
        }

        if (emf == null) {
            throw new IllegalStateException("CourseService is not properly initialized.");
        }

        EntityManager em = emf.createEntityManager();
        try {
            CourseDAO dao = new CourseDAO(em);
            return dao.findByDepartmentName(deptName);
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
