package dev.university.dao;

import dev.university.entity.Section;
import dev.university.entity.SectionId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class SectionDAO {

    private final EntityManagerFactory emf;

    public SectionDAO() {
        this.emf = Persistence.createEntityManagerFactory("hello-jpa");
    }

    public SectionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * 전체 Section 조회
     */
    public List<Section> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Section s", Section.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * 특정 Section 조회 (복합키)
     */
    public Section findById(String courseId, String secId, String semester, int year) {
        EntityManager em = emf.createEntityManager();
        try {
            SectionId id = new SectionId(courseId, secId, semester, year);
            return em.find(Section.class, id);
        } finally {
            em.close();
        }
    }
}
