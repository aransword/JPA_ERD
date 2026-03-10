package dev.university.service;

import dev.university.dto.SectionDTO;
import dev.university.entity.Section;
import dev.university.entity.SectionId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.stream.Collectors;

public class SectionService {

    private final EntityManagerFactory emf;

    public SectionService() {
        this.emf = Persistence.createEntityManagerFactory("hello-jpa");
    }

    /**
     * 특정 Section의 학생 목록 조회 (DTO 호출 체인)
     */
    public SectionDTO findSectionWithStudents(String courseId, String secId, String semester, int year) {
        EntityManager em = emf.createEntityManager();
        try {
            SectionId id = new SectionId(courseId, secId, semester, year);
            Section section = em.find(Section.class, id);

            if (section == null) {
                return null;
            }

            return SectionDTO.from(section);
        } finally {
            em.close();
        }
    }

    /**
     * 전체 Section별 학생 목록 조회
     */
    public List<SectionDTO> findAllSectionsWithStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Section> sections = em.createQuery("SELECT s FROM Section s", Section.class)
                    .getResultList();

            return sections.stream()
                    .map(SectionDTO::from)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}
