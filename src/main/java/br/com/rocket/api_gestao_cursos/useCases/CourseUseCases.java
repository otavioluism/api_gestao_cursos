package br.com.rocket.api_gestao_cursos.useCases;

import br.com.rocket.api_gestao_cursos.dto.CourseRequestDTO;
import br.com.rocket.api_gestao_cursos.entity.CoursesEntity;
import br.com.rocket.api_gestao_cursos.exceptions.UserNotFoundException;
import br.com.rocket.api_gestao_cursos.repository.CoursesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<CoursesEntity> listCourses(String name, String category) {
        List listCourses = new ArrayList();

        var courses = this.coursesRepository.findByNameOrCategory(name, category);

        if (courses.isEmpty()){
            return this.coursesRepository.findAll();
        }

        return courses;
    }

    public CoursesEntity updateCourse(UUID id, CourseRequestDTO courseRequestDTO) {
        var course = this.coursesRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Course not found!"));

        course.setName(courseRequestDTO.getName());
        course.setActive(courseRequestDTO.getActive());
        course.setCategory(courseRequestDTO.getCategory());

        this.coursesRepository.save(course);

        return course;
    }

    public CoursesEntity activeCourse(UUID id) {
        var course = this.coursesRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Course not found!"));

        course.setActive(!course.isActive());

        this.coursesRepository.save(course);

        return course;
    }

    public void deleteCourse(UUID id) {
        this.coursesRepository.deleteById(id);
    }

}
