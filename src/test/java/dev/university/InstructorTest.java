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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InstructorTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello-jpa");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();

    InstructorDao instructorDao = new InstructorDao(manager);

    @Test
    @DisplayName("Srinivasan 교수의 2017년 가을 학기 모든 강의 정보를 상세 조회하면 두 과목이 나온다")
    void instructorScheduleFullInfoTest() {

        List<InstructorScheduleDto> scheduleList = instructorDao.findWeeklySchedule("10101", 2017, "Fall");

        System.out.println("\n================ [ 강의 시간표 조회 결과 ] ================");
        for (InstructorScheduleDto dto : scheduleList) {
            System.out.println("교수 ID: " + dto.getInstructorId());
            System.out.println("교수 성함: " + dto.getInstructorName());
            System.out.println("과목 ID : " + dto.getCourseId());
            System.out.println("학기/연도 : " + dto.getSemester() + " / " + dto.getYear());
            System.out.println("강의 장소 : " + dto.getBuilding() + "동 " + dto.getRoomNumber() + "호");
            System.out.println("---------------------------------------------------------");
        }
        System.out.println("=========================================================\n");

        assertEquals(2, scheduleList.size(), "2017년 가을 학기 강의는 2개");

        manager.close();
        factory.close();
    }
}