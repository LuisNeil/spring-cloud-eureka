package com.formacionbdi.microservicios.app.cursos.controllers;

import com.formacionbdi.microservicios.app.cursos.models.entity.Course;
import com.formacionbdi.microservicios.app.cursos.models.entity.CourseStudent;
import com.formacionbdi.microservicios.app.cursos.services.CourseService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.models.entity.Exam;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseController extends CommonController<Course, CourseService> {

    @Value("${config.balancer.test}")
    private String testBalancer;

    @GetMapping("/test-balancer")
    public ResponseEntity<?> testBalancer() {

        Map<String, Object> response = new HashMap<>();
        response.put("balancer", testBalancer);
        response.put("courses", service.findAll());
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> list() {
        List<Course> courses = ((List<Course>) service.findAll())
                .stream()
                .peek(c-> c.getCourseStudents().forEach(cs -> {
            Student student = new Student();
            student.setId(cs.getStudentId());
            c.addStudent(student);
        })).collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }

    @Override
    @GetMapping({"/{id}"})
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<Course> o = this.service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Course course = o.get();
        if(!course.getCourseStudents().isEmpty()){
            List<Long> ids = course
                    .getCourseStudents()
                    .stream()
                    .map(CourseStudent::getStudentId)
                    .collect(Collectors.toList());
            List<Student> students = (List<Student>) service.getStudentsByCourse(ids);
            course.setStudents(students);
        }
        return ResponseEntity.ok().body(course);
    }

    @Override
    @GetMapping({"/page"})
    public ResponseEntity<?> list(Pageable pageable) {
        Page<Course> courses = service.findAll(pageable).map(course -> {
            course.getCourseStudents().forEach(cs->{
                Student student = new Student();
                student.setId(cs.getStudentId());
                course.addStudent(student);
            });
            return course;
        });
        return ResponseEntity.ok().body(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody Course course, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors())
            return this.validate(result);

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
        students.forEach(s->{
            CourseStudent cs = new CourseStudent();
            cs.setStudentId(s.getId());
            cs.setCourse(courseDb);
            courseDb.addCourseStudents(cs);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
    }

    @PutMapping("/{id}/delete-student")
    public ResponseEntity<?> deleteStudent(@RequestBody Student student, @PathVariable Long id) {
        Optional<Course> o = this.service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course courseDb = o.get();
        CourseStudent cs = new CourseStudent();
        cs.setStudentId(student.getId());
        courseDb.removeCourseStudents(cs);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> findByStudentID(@PathVariable Long studentId){
        Course course = service.findCourseByStudentId(studentId);
        if(course != null){
            List<Long> examsIds = (List<Long>) service.findExamsIdsWithAnswerByStudentId(studentId);
            List<Exam> exams = course.getExams().stream().peek(e -> {
                if (examsIds.contains(e.getId())){
                    e.setAnswered(true);
                }
            }).collect(Collectors.toList());

            course.setExams(exams);
        }
        return ResponseEntity.ok(course);
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

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<?> deleteCourseStudentById(@PathVariable Long id){
        service.deleteCourseStudentById(id);
        return ResponseEntity.noContent().build();
    }

}
