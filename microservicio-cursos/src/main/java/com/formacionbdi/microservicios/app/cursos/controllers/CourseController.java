package com.formacionbdi.microservicios.app.cursos.controllers;

import com.formacionbdi.microservicios.app.cursos.models.entity.Course;
import com.formacionbdi.microservicios.app.cursos.services.CourseService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.models.entity.Exam;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController extends CommonController<Course, CourseService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Course course, @PathVariable Long id) {
        Optional<Course> o = this.service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Course courseDb = o.get();
        courseDb.setName(course.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
    }

    @PutMapping("/{id}/assign-students")
    public ResponseEntity<?> assignStudents(@RequestBody List<Student> students, @PathVariable Long id) {
        Optional<Course> o = this.service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course courseDb = o.get();
        students.forEach(courseDb::addStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
    }

    @PutMapping("/{id}/delete-student")
    public ResponseEntity<?> deleteStudent(@RequestBody Student student, @PathVariable Long id) {
        Optional<Course> o = this.service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course courseDb = o.get();
        courseDb.deleteStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> findByStudentID(@PathVariable Long id){
        return ResponseEntity.ok(service.findCourseByStudentId(id));
    }

    @PutMapping("/{id}/assign-exams")
    public ResponseEntity<?> assignExams(@RequestBody List<Exam> exams, @PathVariable Long id) {
        Optional<Course> o = this.service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course courseDb = o.get();
        exams.forEach(courseDb::addExam);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
    }

    @PutMapping("/{id}/delete-exam")
    public ResponseEntity<?> deleteExam(@RequestBody Exam exam, @PathVariable Long id) {
        Optional<Course> o = this.service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course courseDb = o.get();
        courseDb.removeExam(exam);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
    }

}
