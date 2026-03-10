package dev.university;

import dev.university.entity.Course;
import dev.university.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("hello-jpa");
    EntityManager manager = factory.createEntityManager();

    @Test
    @DisplayName("학과별 과목 조회 - 컴퓨터공학과")
    void testFindCoursesByCompSciDepartment() {
        List<Course> courses = manager.createQuery(
                        "select c from Course c " +
                                "where c.department.deptName = :deptName " +
                                "order by c.courseId",
                        Course.class
                ).setParameter("deptName", "Comp. Sci.")
                .getResultList();

        System.out.println("courses = " + courses.size());
        for (Course course : courses) {
            System.out.println(
                    "courseId = " + course.getCourseId()
                            + ", title = " + course.getTitle()
                            + ", credits = " + course.getCredits()
            );
        }

        assertEquals(5, courses.size());
        assertEquals("CS-101", courses.get(0).getCourseId());
        assertEquals("CS-190", courses.get(1).getCourseId());
        assertEquals("CS-315", courses.get(2).getCourseId());
        assertEquals("CS-319", courses.get(3).getCourseId());
        assertEquals("CS-347", courses.get(4).getCourseId());
    }

    @Test
    @DisplayName("학과별 과목 조회 - 물리학과")
    void testFindCoursesByPhysicsDepartment() {
        List<Course> courses = manager.createQuery(
                        "select c from Course c " +
                                "where c.department.deptName = :deptName " +
                                "order by c.courseId",
                        Course.class
                ).setParameter("deptName", "Physics")
                .getResultList();

        System.out.println("courses = " + courses.size());
        for (Course course : courses) {
            System.out.println(
                    "courseId = " + course.getCourseId()
                            + ", title = " + course.getTitle()
            );
        }

        assertEquals(1, courses.size());
        assertEquals("PHY-101", courses.get(0).getCourseId());
        assertEquals("Physical Principles", courses.get(0).getTitle());
    }

    @Test
    @DisplayName("학과별 과목 조회 - 존재하지 않는 학과")
    void testFindCoursesByInvalidDepartment() {
        List<Course> courses = manager.createQuery(
                        "select c from Course c " +
                                "where c.department.deptName = :deptName " +
                                "order by c.courseId",
                        Course.class
                ).setParameter("deptName", "Korean")
                .getResultList();

        System.out.println("courses = " + courses.size());

        assertTrue(courses.isEmpty());
    }

    @Test
    @DisplayName("학과 엔티티 조회")
    void testFindDepartment() {
        Department department = manager.find(Department.class, "Comp. Sci.");

        System.out.println("department name = " + department.getDeptName());
        System.out.println("building = " + department.getBuilding());
        System.out.println("budget = " + department.getBudget());

        assertNotNull(department);
        assertEquals("Comp. Sci.", department.getDeptName());
        assertEquals("Taylor", department.getBuilding());
    }
}
