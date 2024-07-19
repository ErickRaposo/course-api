package com.erick.apiCursos.domain.course;

import com.erick.apiCursos.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String category;
    private String teacher;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Course(CourseRequestDTO data, User user){
        this.name = data.name();
        this.category = data.category();
        this.teacher = data.teacher();
        this.user = user;
    }
}
