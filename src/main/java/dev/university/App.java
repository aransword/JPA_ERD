package dev.university;

import dev.university.dto.SectionDTO;
import dev.university.dto.StudentDTO;
import dev.university.entity.Section;
import dev.university.entity.SectionId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            // 특정 Section 조회: CS-190, sec_id='2', Spring, 2017
            SectionId sectionId = new SectionId("CS-190", "2", "Spring", 2017);
            Section section = em.find(Section.class, sectionId);

            if (section == null) {
                System.out.println("❌ Section을 찾을 수 없습니다.");
                return;
            }

            // DTO 호출 체인으로 변환: Section → Takes → Student → StudentDTO
            SectionDTO sectionDTO = SectionDTO.from(section);

            System.out.println("=== Section 정보 ===");
            System.out.println("Course ID: " + sectionDTO.getCourseId());
            System.out.println("Sec ID: " + sectionDTO.getSecId());
            System.out.println("Semester: " + sectionDTO.getSemester());
            System.out.println("Year: " + sectionDTO.getYear());

            System.out.println("\n=== 수강 학생 목록 ===");
            List<StudentDTO> students = sectionDTO.getStudents();
            if (students.isEmpty()) {
                System.out.println("(수강 학생 없음)");
            } else {
                for (StudentDTO s : students) {
                    System.out.println(s);
                }
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
