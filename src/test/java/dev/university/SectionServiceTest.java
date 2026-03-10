package dev.university;

import dev.university.dto.SectionDTO;
import dev.university.dto.StudentDTO;
import dev.university.service.SectionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SectionServiceTest {

    SectionService sectionService = new SectionService();

    @Test
    @DisplayName("특정 Section의 수강 학생 조회 - CS-101, 1, Fall, 2017")
    void testFindStudentsByCS101Fall2017() {
        SectionDTO section = sectionService.findSectionWithStudents("CS-101", "1", "Fall", 2017);

        assertNotNull(section);
        assertEquals("CS-101", section.getCourseId());
        assertEquals("1", section.getSecId());
        assertEquals("Fall", section.getSemester());
        assertEquals(2017, section.getYear());

        List<StudentDTO> students = section.getStudents();
        System.out.println("=== CS-101 / 1 / Fall / 2017 수강 학생 ===");
        for (StudentDTO s : students) {
            System.out.println(s);
        }

        // takes 데이터 기준: 00128, 12345, 45678, 54321, 76543, 98765 → 6명
        assertEquals(6, students.size());
    }

    @Test
    @DisplayName("특정 Section의 수강 학생 조회 - CS-190, 2, Spring, 2017")
    void testFindStudentsByCS190Spring2017() {
        SectionDTO section = sectionService.findSectionWithStudents("CS-190", "2", "Spring", 2017);

        assertNotNull(section);
        assertEquals("CS-190", section.getCourseId());
        assertEquals("2", section.getSecId());

        List<StudentDTO> students = section.getStudents();
        System.out.println("=== CS-190 / 2 / Spring / 2017 수강 학생 ===");
        for (StudentDTO s : students) {
            System.out.println(s);
        }

        // takes 데이터 기준: 12345(Shankar), 54321(Williams) → 2명
        assertEquals(2, students.size());
    }

    @Test
    @DisplayName("수강 학생이 1명인 Section 조회 - BIO-101, 1, Summer, 2017")
    void testFindStudentsByBIO101Summer2017() {
        SectionDTO section = sectionService.findSectionWithStudents("BIO-101", "1", "Summer", 2017);

        assertNotNull(section);

        List<StudentDTO> students = section.getStudents();
        System.out.println("=== BIO-101 / 1 / Summer / 2017 수강 학생 ===");
        for (StudentDTO s : students) {
            System.out.println(s);
        }

        // takes 데이터 기준: 98988(Tanaka) → 1명
        assertEquals(1, students.size());
        assertEquals("Tanaka", students.get(0).getName());
    }

    @Test
    @DisplayName("존재하지 않는 Section 조회")
    void testFindStudentsByInvalidSection() {
        SectionDTO section = sectionService.findSectionWithStudents("CS-999", "1", "Fall", 2025);

        System.out.println("=== 존재하지 않는 Section 조회 ===");
        System.out.println("result = " + section);

        assertNull(section);
    }

    @Test
    @DisplayName("전체 Section별 수강 학생 조회")
    void testFindAllSectionsWithStudents() {
        List<SectionDTO> sections = sectionService.findAllSectionsWithStudents();

        System.out.println("=== 전체 Section 목록 (총 " + sections.size() + "개) ===");
        for (SectionDTO s : sections) {
            System.out.println(s.getCourseId() + " / " + s.getSecId()
                    + " / " + s.getSemester() + " / " + s.getYear()
                    + " → 학생 수: " + s.getStudents().size());
        }

        // section 데이터 기준: 총 15개 section
        assertEquals(15, sections.size());
    }
}
