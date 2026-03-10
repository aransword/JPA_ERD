package dev.university.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InstructorScheduleDto {
    private String instructorId;
    private String instructorName;
    private String courseId;
    private String semester;
    private Integer year;
    private String building;
    private String roomNumber;
}
