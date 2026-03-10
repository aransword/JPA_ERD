package dev.university;

import dev.university.entity.Course;
import dev.university.service.CourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseServiceTest {

    @Test
    @DisplayName("학과별 과목 조회 - 컴퓨터공학과")
    void testFindCoursesByCompSciDepartment() {
        CourseService courseService = new CourseService();
        List<Course> courses = courseService.getCoursesByDepartment("Comp. Sci.");

        System.out.println("=== 컴퓨터공학과 과목 목록 ===");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + " / " + course.getTitle());
        }

        assertEquals(5, courses.size());
        assertEquals("CS-101", courses.get(0).getCourseId());
        assertEquals("CS-190", courses.get(1).getCourseId());
        assertEquals("CS-315", courses.get(2).getCourseId());
        assertEquals("CS-319", courses.get(3).getCourseId());
        assertEquals("CS-347", courses.get(4).getCourseId());

        courseService.close();
    }

    @Test
    @DisplayName("학과별 과목 조회 - 물리학과")
    void testFindCoursesByPhysicsDepartment() {
        CourseService courseService = new CourseService();
        List<Course> courses = courseService.getCoursesByDepartment("Physics");

        System.out.println("=== 물리학과 과목 목록 ===");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + " / " + course.getTitle());
        }

        assertEquals(1, courses.size());
        assertEquals("PHY-101", courses.get(0).getCourseId());
        assertEquals("Physical Principles", courses.get(0).getTitle());

        courseService.close();
    }

    @Test
    @DisplayName("학과별 과목 조회 - 존재하지 않는 학과")
    void testFindCoursesByInvalidDepartment() {
        CourseService courseService = new CourseService();
        List<Course> courses = courseService.getCoursesByDepartment("Korean");

        System.out.println("=== 존재하지 않는 학과 조회 ===");
        System.out.println("courses = " + courses.size());

        assertTrue(courses.isEmpty());

        courseService.close();
    }
}
