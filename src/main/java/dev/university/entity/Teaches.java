package dev.university.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(Teaches.TeachesId.class)
public class Teaches {

    @Id @Column(name = "ID")
    private String id;

    @Id @Column(name = "course_id")
    private String courseId;

    @Id @Column(name = "sec_id")
    private String secId;

    @Id @Column(name = "semester")
    private String semester;

    @Id @Column(name = "year")
    private Integer year;

    // Section 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false),
            @JoinColumn(name = "sec_id", referencedColumnName = "sec_id", insertable = false, updatable = false),
            @JoinColumn(name = "semester", referencedColumnName = "semester", insertable = false, updatable = false),
            @JoinColumn(name = "year", referencedColumnName = "year", insertable = false, updatable = false)
    })
    private Section section;

    // Instructor 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private Instructor instructor;

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    static class TeachesId implements Serializable {
        private String id;
        private String courseId;
        private String secId;
        private String semester;
        private Integer year;
    }
}
