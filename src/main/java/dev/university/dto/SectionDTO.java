package dev.university.dto;

import dev.university.entity.Section;
import dev.university.entity.Takes;

import java.util.List;
import java.util.stream.Collectors;

public class SectionDTO {
    private String courseId;
    private String secId;
    private String semester;
    private Integer year;
    private String timeSlotId;
    private List<StudentDTO> students;

    public SectionDTO(String courseId, String secId, String semester, Integer year,
            String timeSlotId, List<StudentDTO> students) {
        this.courseId = courseId;
        this.secId = secId;
        this.semester = semester;
        this.year = year;
        this.timeSlotId = timeSlotId;
        this.students = students;
    }

    /**
     * Section 엔티티 → SectionDTO 변환 (DTO 호출 체인)
     * Section.getTakes() → Takes.getStudent() → StudentDTO.from()
     */
    public static SectionDTO from(Section section) {
        // 🌟 DTO 호출 체인: Section → Takes → Student → StudentDTO
        List<StudentDTO> students = section.getTakes().stream()
                .map(Takes::getStudent)
                .map(StudentDTO::from)
                .collect(Collectors.toList());

        return new SectionDTO(
                section.getCourse().getCourseId(),
                section.getSecId(),
                section.getSemester(),
                section.getYear(),
                section.getTimeSlotId(),
                students);
    }

    public String getCourseId() {
        return courseId;
    }

    public String getSecId() {
        return secId;
    }

    public String getSemester() {
        return semester;
    }

    public Integer getYear() {
        return year;
    }

    public String getTimeSlotId() {
        return timeSlotId;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "SectionDTO{" +
                "courseId='" + courseId + '\'' +
                ", secId='" + secId + '\'' +
                ", semester='" + semester + '\'' +
                ", year=" + year +
                ", students=" + students +
                '}';
    }
}
