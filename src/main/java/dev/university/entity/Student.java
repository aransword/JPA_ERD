package dev.university.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;

@Entity
@Table(name = "student")
@Getter
public class Student {

    @Id
    @Column(name = "ID")
    private String id;

    private String name;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "tot_cred")
    private Integer totCred;

//    @OneToMany(mappedBy = "student")
//    private List<Takes> takes = new ArrayList<>();

    protected Student() {}
}