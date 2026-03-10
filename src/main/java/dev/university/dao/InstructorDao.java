package dev.university.dao;

import dev.university.dto.InstructorScheduleDto;
import jakarta.persistence.EntityManager;
import java.util.List;

public class InstructorDao {
    private final EntityManager em;

    public InstructorDao(EntityManager em) {
        this.em = em;
    }

    public List<InstructorScheduleDto> findWeeklySchedule(String instructorId, Integer year, String semester) {
        String jpql = "SELECT new dev.university.dto.InstructorScheduleDto(" +
                "i.id, i.name, t.courseId, t.semester, t.year, s.building, s.roomNumber) " +
                "FROM Teaches t " +
                "JOIN t.instructor i " +
                "JOIN t.section s " +
                "WHERE i.id = :id AND t.year = :year AND t.semester = :semester";

        return em.createQuery(jpql, InstructorScheduleDto.class)
                .setParameter("id", instructorId)
                .setParameter("year", year)
                .setParameter("semester", semester)
                .getResultList();
    }
}