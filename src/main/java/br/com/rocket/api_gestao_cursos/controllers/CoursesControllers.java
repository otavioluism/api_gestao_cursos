package br.com.rocket.api_gestao_cursos.controllers;


import br.com.rocket.api_gestao_cursos.dto.CourseRequestDTO;
import br.com.rocket.api_gestao_cursos.useCases.CourseUseCases;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CoursesControllers {

    private final CourseUseCases courseUseCases;

    private CoursesControllers(CourseUseCases courseUseCases){
        this.courseUseCases = courseUseCases;
    }

    @PostMapping("/")
    public ResponseEntity create(@Valid @RequestBody CourseRequestDTO courseRequestDTO){
        try {
            var course = this.courseUseCases.createCourse(courseRequestDTO);
            return ResponseEntity.ok().body(course);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Error on create entity");
        }
    }

    @GetMapping("/")
    public ResponseEntity list(){
        try {
            var courses = this.courseUseCases.listCourses();
            return ResponseEntity.ok().body(courses);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Error on list all entity");
        }

    }

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") UUID id){}

    @PatchMapping("/{id}")
    public void active(@PathVariable(name = "id") UUID id){}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") UUID id){}
}
