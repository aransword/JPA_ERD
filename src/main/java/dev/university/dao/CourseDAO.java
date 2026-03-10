package dev.university.dao;

import dev.university.entity.Course;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseDAO {

    private final EntityManager em;

    public CourseDAO(EntityManager em) {
        this.em = em;
    }

    public List<Course> findByDepartmentName(String deptName) {
        return em.createQuery(
                        "select c from Course c " +
                                "where c.department.deptName = :deptName " +
                                "order by c.courseId",
                        Course.class
                ).setParameter("deptName", deptName)
                .getResultList();
    }
}