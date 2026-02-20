package br.com.rocket.api_gestao_cursos.useCases;

import br.com.rocket.api_gestao_cursos.dto.CourseRequestDTO;
import br.com.rocket.api_gestao_cursos.entity.CoursesEntity;
import br.com.rocket.api_gestao_cursos.repository.CoursesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseUseCases {

    private final CoursesRepository coursesRepository;

    private CourseUseCases(CoursesRepository coursesRepository){
        this.coursesRepository = coursesRepository;
    }

    public CoursesEntity createCourse(CourseRequestDTO courseRequestDTO){
        var courseEntity = CoursesEntity.builder()
                .name(courseRequestDTO.getName())
                .active(courseRequestDTO.getActive())
                .category(courseRequestDTO.getCategory())
                .build();

        return this.coursesRepository.save(courseEntity);
    }

    public List<CoursesEntity> listCourses() {
        return this.coursesRepository.findAll();
    }

}
