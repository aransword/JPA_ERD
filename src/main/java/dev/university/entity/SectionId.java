package dev.university.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SectionId {
    private String course; // 엔티티 필드명 "course"와 일치 (타입은 Course PK인 String)
    private String secId; // 엔티티 필드명 "secId"와 일치
    private String semester;
    private Integer year;
}
