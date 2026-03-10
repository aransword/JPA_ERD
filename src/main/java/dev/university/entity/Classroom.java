package dev.university.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@IdClass(Classroom.ClassroomID.class)
public class Classroom {
    @Id
    private String building;

    @Id
    @Column(name="room_number")
    private String roomNumber;

    private int capacity;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class ClassroomID implements Serializable {
        private String building;
        private String roomNumber;
    }
}
