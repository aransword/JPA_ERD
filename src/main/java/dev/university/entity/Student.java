package dev.university.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "student")
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