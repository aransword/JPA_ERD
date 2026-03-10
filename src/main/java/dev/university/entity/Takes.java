package dev.university.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "takes")
@IdClass(TakeId.class)
public class Takes {

    @Id
    @Column(name = "id")
    private String studentId;

    @Id
    @Column(name = "course_id")
    private String courseId;

    @Id
    @Column(name = "sec_id")
    private String secId;

    @Id
    @Column(name = "semester")
    private String semester;

    @Id
    @Column(name = "year")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "ID", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false),
            @JoinColumn(name = "sec_id", referencedColumnName = "sec_id", insertable = false, updatable = false),
            @JoinColumn(name = "semester", referencedColumnName = "semester", insertable = false, updatable = false),
            @JoinColumn(name = "year", referencedColumnName = "year", insertable = false, updatable = false)
    })
    private Section section;

    @Column(name = "grade", length = 2)
    private String grade;

    public Takes() {
    }

    public Takes(String studentId, String courseId, String secId, String semester, Integer year,
                 Student student, Section section, String grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.secId = secId;
        this.semester = semester;
        this.year = year;
        this.student = student;
        this.section = section;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
