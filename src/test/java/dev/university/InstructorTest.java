package dev.university;

import dev.university.dao.InstructorDao;
import dev.university.dto.InstructorScheduleDto;
import dev.university.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InstructorTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello-jpa");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();

    InstructorDao instructorDao = new InstructorDao(manager);

    @Test
    @DisplayName("교수 ID로 일주일 시간표와 강의실 정보를 정확히 조회한다")
    void getInstructorWeeklyScheduleTest() {
        transaction.begin();

        try {
            // 1. 기초 데이터 삽입

            // 강의실
            Classroom classroom = new Classroom();
            classroom.setBuilding("Taylor");
            classroom.setRoomNumber("3128");
            classroom.setCapacity(50);
            manager.persist(classroom);

            // 학과
            Department dept = new Department();
            dept.setDeptName("Comp. Sci.");
            dept.setBuilding("Taylor");
            dept.setBudget(new BigDecimal("100000.00"));
            manager.persist(dept);

            // 교수
            Instructor prof = new Instructor();
            prof.setId("10101");
            prof.setName("Srinivasan");
            prof.setSalary(new BigDecimal("65000.00"));
            prof.setDepartment(dept);
            manager.persist(prof);

            // 과목
            Course course = new Course();
            course.setCourseId("CS-101");
            course.setTitle("Intro. to Computer Science");
            course.setCredits(4);
            course.setDepartment(dept);
            manager.persist(course);

            // 섹션
            Section section = new Section();
            section.setCourseId("CS-101");
            section.setSecId("1");
            section.setSemester("Fall");
            section.setYear(2025);
            section.setClassroom(classroom);
            section.setTimeSlotId("A");
            manager.persist(section);

            // 강의 배정
            Teaches teaches = new Teaches();
            teaches.setId("10101");
            teaches.setCourseId("CS-101");
            teaches.setSecId("1");
            teaches.setSemester("Fall");
            teaches.setYear(2025);
            teaches.setInstructor(prof);
            teaches.setSection(section);
            manager.persist(teaches);

            // 영속성 컨텍스트를 DB에 반영하고 비움 (실제 DB 조회를 시뮬레이션)
            manager.flush();
            manager.clear();

            //조회
            List<InstructorScheduleDto> schedule = instructorDao.findWeeklySchedule("10101", 2025, "Fall");

            assertFalse(schedule.isEmpty(), "시간표가 비어있으면 안 됩니다.");
            InstructorScheduleDto dto = schedule.get(0);

            assertEquals("Srinivasan", dto.getInstructorName());
            assertEquals("CS-101", dto.getCourseId());
            assertEquals("Taylor", dto.getBuilding());
            assertEquals("3128", dto.getRoomNumber());
            assertEquals(2025, dto.getYear());

            System.out.println("조회 결과: " + dto.getInstructorName() + " 교수는 "
                    + dto.getBuilding() + "동 " + dto.getRoomNumber() + "호에서 강의합니다.");

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            manager.close();
            factory.close();
        }
    }
}