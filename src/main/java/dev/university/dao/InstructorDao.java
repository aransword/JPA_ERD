package dev.university.dao;

import dev.university.dto.InstructorScheduleDto;
import dev.university.entity.Teaches;
import jakarta.persistence.EntityManager;
import java.util.List;

public class InstructorDao {
    private final EntityManager em;

    public InstructorDao(EntityManager em) {
        this.em = em;
    }

    public List<Teaches> findWeeklySchedule(String instructorId, Integer year, String semester) {
        String jpql = "SELECT t FROM Teaches t " +
                "JOIN FETCH t.instructor i " +
                "JOIN FETCH t.section s " +
                "WHERE i.id = :id AND t.year = :year AND t.semester = :semester";

        return em.createQuery(jpql, Teaches.class)
                .setParameter("id", instructorId)
                .setParameter("year", year)
                .setParameter("semester", semester)
                .getResultList();
    }
}