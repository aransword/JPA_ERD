package dev.university.service;

import dev.university.dao.ClassroomDAO;
import dev.university.dto.ClassroomDTO;
import dev.university.entity.Section;

import java.util.List;
import java.util.stream.Collectors;

public class ClassroomService {

    private final ClassroomDAO classroomDAO;

    public ClassroomService() {
        this.classroomDAO = new ClassroomDAO();
    }

    public List<ClassroomDTO> getSectionsByClassroom(String building, String roomNumber) {
        List<Section> sections = classroomDAO.findSectionsByClassroom(building, roomNumber);

        return sections.stream()
                .map(section -> new ClassroomDTO(
                        section.getCourse().getCourseId(),
                        section.getSecId(),
                        section.getSemester(),
                        section.getYear(),
                        section.getBuilding(),
                        section.getRoomNumber(),
                        section.getTimeSlotId()
                ))
                .collect(Collectors.toList());
    }
}