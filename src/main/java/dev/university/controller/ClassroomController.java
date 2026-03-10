package dev.university.controller;

import dev.university.dto.ClassroomDTO;
import dev.university.service.ClassroomService;

import java.util.List;

public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController() {
        this.classroomService = new ClassroomService();
    }

    public List<ClassroomDTO> getSectionsByClassroom(String building, String roomNumber) {
        return classroomService.getSectionsByClassroom(building, roomNumber);
    }
}