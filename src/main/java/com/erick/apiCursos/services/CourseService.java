package com.erick.apiCursos.services;

import com.erick.apiCursos.domain.course.Course;
import com.erick.apiCursos.domain.course.CourseRequestDTO;
import com.erick.apiCursos.domain.course.CourseResponseDTO;
import com.erick.apiCursos.domain.user.User;
import com.erick.apiCursos.repositories.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;

    public CourseResponseDTO createCourse(CourseRequestDTO data, User user) {
        Course newCourse = new Course(data, user);

        this.repository.save(newCourse);

        return new CourseResponseDTO(newCourse.getId(), newCourse.getName(), newCourse.getCategory(), newCourse.getTeacher(), newCourse.getUser().getId());
    }

    public List<CourseResponseDTO> getCourseByEmail(UUID userId){
        List<Course> courses = this.repository.findByUserId(userId);

        return courses.stream().map(course -> new CourseResponseDTO(course.getId(), course.getName(), course.getCategory(), course.getTeacher(), course.getUser().getId())).toList();
    }

    public CourseResponseDTO updateCourse(UUID id, UUID userId, CourseRequestDTO data){
        Optional<Course> course = this.repository.findByIdAndUserId(id, userId);

        if (course.isPresent()){
            Course rawCourse = course.get();
            rawCourse.setName(data.name());
            rawCourse.setCategory(data.category());
            rawCourse.setTeacher(data.teacher());

            this.repository.save(rawCourse);

            return new CourseResponseDTO(rawCourse.getId(), rawCourse.getName(), rawCourse.getCategory(), rawCourse.getTeacher(), rawCourse.getUser().getId());
        }

        return null;
    }

    @Transactional
    public void deleteCourse(UUID id, UUID userId){
        repository.deleteByIdAndUserId(id, userId);
    }
}
