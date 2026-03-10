package dev.university;

import dev.university.dto.ClassroomDTO;
import dev.university.service.ClassroomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassroomServiceTest {

    ClassroomService classroomService = new ClassroomService();

    @Test
    @DisplayName("강의실별 개설 강의 조회 - Packard 101")
    void testFindSectionsByPackard101() {

        List<ClassroomDTO> result =
                classroomService.getSectionsByClassroom("Packard", "101");

        System.out.println("sections = " + result.size());

        for (ClassroomDTO dto : result) {
            System.out.println(
                    "courseId = " + dto.getCourseId()
                            + ", secId = " + dto.getSecId()
                            + ", semester = " + dto.getSemester()
                            + ", year = " + dto.getYear()
                            + ", building = " + dto.getBuilding()
                            + ", roomNumber = " + dto.getRoomNumber()
                            + ", timeSlotId = " + dto.getTimeSlotId()
            );
        }

        assertEquals(4, result.size());

        for (ClassroomDTO dto : result) {
            assertEquals("Packard", dto.getBuilding());
            assertEquals("101", dto.getRoomNumber());
        }
    }

    @Test
    @DisplayName("강의실별 개설 강의 조회 - 존재하지 않는 강의실")
    void testFindSectionsByInvalidClassroom() {

        List<ClassroomDTO> result =
                classroomService.getSectionsByClassroom("Packard", "999");

        System.out.println("sections = " + result.size());

        assertTrue(result.isEmpty());
    }
}