package com.formacionbdi.microservicios.app.cursos.services;

import com.formacionbdi.microservicios.app.cursos.models.entity.Course;
import com.formacionbdi.microservicios.commons.services.CommonService;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CourseService extends CommonService<Course> {

    public Course findCourseByStudentId(Long id);

    public Iterable<Long> findExamsIdsWithAnswerByStudentId(Long studentId);

    public Iterable<Student> getStudentsByCourse(@RequestParam List<Long> ids);


}
