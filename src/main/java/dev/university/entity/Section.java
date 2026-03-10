package dev.university.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@IdClass(Section.SectionID.class)
public class Section {
    // 🌟 1. PK이면서 동시에 FK인 경우 (@Id와 @ManyToOne을 같이 씁니다)
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // 실제 DB의 컬럼명
    private Course course;

    // 🌟 2. 나머지 일반 PK들 (그냥 @Id만 붙여줍니다)
    @Id
    @Column(name = "sec_id")
    private String secId;

    @Id
    private String semester;

    @Id
    private Integer year;

    // 🌟 3. 다른 테이블의 '복합키'를 외래키로 참조할 때 (@JoinColumns 사용)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "building", referencedColumnName = "building"),
            @JoinColumn(name = "room_number", referencedColumnName = "room_number")
    })
    private Classroom classroom;

    // 4. 일반 컬럼
    @Column(name = "time_slot_id")
    private String timeSlotId;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class SectionID implements Serializable {
        private String course_id;
        private String sec_id;
        private String semester;
        private int year;
    }
}
