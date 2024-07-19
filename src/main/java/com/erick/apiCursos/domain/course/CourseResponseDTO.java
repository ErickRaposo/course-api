package com.erick.apiCursos.domain.course;

import java.util.UUID;

public record CourseResponseDTO (UUID id, String name, String category, String teacher, UUID userId){
}
