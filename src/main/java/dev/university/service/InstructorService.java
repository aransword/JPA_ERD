package dev.university.service;

import dev.university.dao.InstructorDao;
import dev.university.dto.InstructorScheduleDto;
import dev.university.entity.Teaches;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class InstructorService {

    private final InstructorDao instructorDao;

    public InstructorService(EntityManager em) {
        this.instructorDao = new InstructorDao(em);
    }

    public List<InstructorScheduleDto> getInstructorSchedule(String instructorId, Integer year, String semester) {
        List<Teaches> teachesList = instructorDao.findWeeklySchedule(instructorId, year, semester);

        //엔티티를 DTO로 변환
        return teachesList.stream()
                .map(t -> new InstructorScheduleDto(
                        t.getInstructor().getId(),       // instructorId
                        t.getInstructor().getName(),     // instructorName
                        t.getCourseId(),                 // courseId
                        t.getSemester(),                 // semester
                        t.getYear(),                     // year
                        t.getSection().getBuilding(),    // building
                        t.getSection().getRoomNumber()   // roomNumber
                ))
                .collect(Collectors.toList());
    }
}