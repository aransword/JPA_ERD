package dev.university.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@IdClass(SectionId.class)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    // PK이면서 동시에 FK인 경우 (@Id와 @ManyToOne을 같이 씁니다)
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // 실제 DB의 컬럼명
    private Course course;

    // 나머지 일반 PK들 (그냥 @Id만 붙여줍니다)
    @Id
    @Column(name = "sec_id")
    private String secId;

    @Id
    private String semester;

    @Id
    private Integer year;

    // 일반 컬럼
    @Column(name = "time_slot_id")
    private String timeSlotId;

    @Column(name = "building")
    private String building;

    @Column(name = "room_number")
    private String roomNumber;

    @OneToMany(mappedBy = "section")
    @Builder.Default
    private List<Takes> takes = new ArrayList<>();
}
