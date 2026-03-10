package dev.university.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
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
