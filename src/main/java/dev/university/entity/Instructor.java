package dev.university.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(nullable = false)
    private String name;

    private BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_name")
    private Department department;
}
