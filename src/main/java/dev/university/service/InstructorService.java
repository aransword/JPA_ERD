package dev.university.service;

import dev.university.dao.InstructorDao;
import dev.university.dto.InstructorScheduleDto;
import jakarta.persistence.EntityManager;
import java.util.List;

public class InstructorService {

    private final InstructorDao instructorDao;

    public InstructorService(EntityManager em) {
        this.instructorDao = new InstructorDao(em);
    }

    public List<InstructorScheduleDto> getInstructorSchedule(String instructorId, Integer year, String semester) {
        List<InstructorScheduleDto> schedule = instructorDao.findWeeklySchedule(instructorId, year, semester);

        return schedule;
    }
}