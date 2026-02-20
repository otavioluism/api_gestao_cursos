package br.com.rocket.api_gestao_cursos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseRequestDTO {
    private String name;
    private String category;
    private Boolean active;
}
