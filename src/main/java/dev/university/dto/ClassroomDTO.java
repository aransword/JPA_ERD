package dev.university.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClassroomDTO {
    private String courseId;
    private String secId;
    private String semester;
    private Integer year;
    private String building;
    private String roomNumber;
    private String timeSlotId;
}