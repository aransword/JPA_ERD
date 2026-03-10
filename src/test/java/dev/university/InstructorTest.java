package dev.university;

import dev.university.dto.InstructorScheduleDto;
import dev.university.service.InstructorService; // 서비스 임포트
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstructorTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello-jpa");
    EntityManager manager = factory.createEntityManager();

    InstructorService instructorService = new InstructorService(manager);

    @Test
    @DisplayName("Service를 통해 Srinivasan 교수의 2017년 가을 학기 정보를 조회한다")
    void instructorScheduleFullInfoTest() {

        List<InstructorScheduleDto> scheduleList =
                instructorService.getInstructorSchedule("10101", 2017, "Fall");

        System.out.println("\n================ [ 서비스 호출 결과 ] ================");
        for (InstructorScheduleDto dto : scheduleList) {
            System.out.println("교수 ID: " + dto.getInstructorId());
            System.out.println("교수 성함: " + dto.getInstructorName());
            System.out.println("과목 ID : " + dto.getCourseId());
            System.out.println("학기/연도 : " + dto.getSemester() + " / " + dto.getYear());
            System.out.println("강의 장소 : " + dto.getBuilding() + "동 " + dto.getRoomNumber() + "호");
            System.out.println("---------------------------------------------------------");
        }
        System.out.println("=========================================================\n");

        assertEquals(2, scheduleList.size(), "2017년 가을 학기 강의는 2개여야 합니다.");

        manager.close();
        factory.close();
    }
}