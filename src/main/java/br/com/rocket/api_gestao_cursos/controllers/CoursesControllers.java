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

    private CoursesControllers(CourseUseCases courseUseCases) {
        this.courseUseCases = courseUseCases;
    }

    @PostMapping("/")
    public ResponseEntity create(@Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        try {
            var course = this.courseUseCases.createCourse(courseRequestDTO);
            return ResponseEntity.ok().body(course);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Error on create entity");
        }
    }

    @GetMapping()
    public ResponseEntity list(@RequestParam String name, @RequestParam String category) {
        try {
            var courses = this.courseUseCases.listCourses(name, category);
            return ResponseEntity.ok().body(courses);
        } catch (Exception ex) {
//            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(name = "id") UUID id, @RequestBody CourseRequestDTO courseRequestDTO) {
        try {
            var course = this.courseUseCases.updateCourse(id, courseRequestDTO);
            return ResponseEntity.ok().body(course);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity active(@PathVariable(name = "id") UUID id) {
        try {
            var course = this.courseUseCases.activeCourse(id);
            return ResponseEntity.ok().body(course);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") UUID id) {
        try {
            this.courseUseCases.deleteCourse(id);
            return ResponseEntity.ok().body("Course delete with success!");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
