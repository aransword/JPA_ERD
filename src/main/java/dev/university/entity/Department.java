package dev.university.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "dept_name")
    private String deptName;

    private String building;

    private Integer budget;

//    @OneToMany(mappedBy = "department")
//    private List<Course> courses = new ArrayList<>();

    protected Department() {}

    public Department(String deptName, String building, Integer budget) {
        this.deptName = deptName;
        this.building = building;
        this.budget = budget;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getBuilding() {
        return building;
    }

    public Integer getBudget() {
        return budget;
    }

//    public List<Course> getCourses() {
//        return courses;
//    }
}