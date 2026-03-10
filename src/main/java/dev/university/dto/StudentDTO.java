package dev.university.dto;

import dev.university.entity.Student;

public class StudentDTO {
    private String id;
    private String name;
    private String deptName;
    private int totCred;

    public StudentDTO(String id, String name, String deptName, int totCred) {
        this.id = id;
        this.name = name;
        this.deptName = deptName;
        this.totCred = totCred;
    }

    public static StudentDTO from(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getDeptName(),
                student.getTotCred());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeptName() {
        return deptName;
    }

    public int getTotCred() {
        return totCred;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deptName='" + deptName + '\'' +
                ", totCred=" + totCred +
                '}';
    }
}
