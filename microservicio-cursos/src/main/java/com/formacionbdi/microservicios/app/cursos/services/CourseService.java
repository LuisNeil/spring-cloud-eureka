package com.formacionbdi.microservicios.app.cursos.services;

import com.formacionbdi.microservicios.app.cursos.models.entity.Course;
import com.formacionbdi.microservicios.commons.services.CommonService;
import org.springframework.web.bind.annotation.PathVariable;

public interface CourseService extends CommonService<Course> {

    public Course findCourseByStudentId(Long id);

    public Iterable<Long> findExamsIdsWithAnswerByStudentId(Long studentId);


}
