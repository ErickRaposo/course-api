package com.erick.apiCursos.controllers;

import com.erick.apiCursos.domain.course.CourseRequestDTO;
import com.erick.apiCursos.domain.course.CourseResponseDTO;
import com.erick.apiCursos.domain.user.User;
import com.erick.apiCursos.infra.security.TokenService;
import com.erick.apiCursos.services.CourseService;
import com.erick.apiCursos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO data, @RequestHeader("Authorization") String authorization){
        String token = this.recoverToken(authorization);
        String email = this.tokenService.validateToken(token);

        User user = this.userService.getUserByEmail(email);

        CourseResponseDTO newCourse = this.courseService.createCourse(data, user);

        return ResponseEntity.ok(newCourse);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getCourses(@RequestHeader("Authorization") String authorization){
        String token = this.recoverToken(authorization);
        String email = this.tokenService.validateToken(token);

        User user = this.userService.getUserByEmail(email);

        List<CourseResponseDTO> allTasks = this.courseService.getCourseByEmail(user.getId());

        return ResponseEntity.ok(allTasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable UUID id, @RequestBody CourseRequestDTO data, @RequestHeader("Authorization") String authorization){
        String token = this.recoverToken(authorization);
        String email = this.tokenService.validateToken(token);

        User user = this.userService.getUserByEmail(email);

        CourseResponseDTO updatedCourse = this.courseService.updateCourse(id, user.getId(), data);

        if (updatedCourse != null) {
            return ResponseEntity.ok(updatedCourse);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteCourse(@PathVariable UUID id, @RequestHeader("Authorization") String authorization){
        String token = this.recoverToken(authorization);
        String email = this.tokenService.validateToken(token);

        User user = this.userService.getUserByEmail(email);

        this.courseService.deleteCourse(id, user.getId());
        return ResponseEntity.noContent().build();
    }

    private String recoverToken(String authorization){
        if (authorization == null) return null;
        return authorization.replace("Bearer ", "");
    }
}
