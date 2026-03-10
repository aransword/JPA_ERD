package dev.university.dao;

import dev.university.entity.Section;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ClassroomDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hello-jpa");

    public List<Section> findSectionsByClassroom(String building, String roomNumber) {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT s FROM Section s " +
                    "WHERE s.building = :building AND s.roomNumber = :roomNumber";

            return em.createQuery(jpql, Section.class)
                    .setParameter("building", building)
                    .setParameter("roomNumber", roomNumber)
                    .getResultList();

        } finally {
            em.close();
        }
    }
}