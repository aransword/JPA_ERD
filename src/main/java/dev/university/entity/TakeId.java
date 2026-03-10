package dev.university.entity;

import java.io.Serializable;
import java.util.Objects;

public class TakeId implements Serializable {

    private String studentId;

    private String courseId;

    private String secId;

    private String semester;

    private Integer year;

    public TakeId() {
    }

    public TakeId(String studentId, String courseId, String secId, String semester, Integer year) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.secId = secId;
        this.semester = semester;
        this.year = year;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TakeId)) {
            return false;
        }
        TakeId takeId = (TakeId) o;
        return Objects.equals(studentId, takeId.studentId)
                && Objects.equals(courseId, takeId.courseId)
                && Objects.equals(secId, takeId.secId)
                && Objects.equals(semester, takeId.semester)
                && Objects.equals(year, takeId.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId, secId, semester, year);
    }
}
