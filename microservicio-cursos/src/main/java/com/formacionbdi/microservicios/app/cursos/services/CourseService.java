package com.formacionbdi.microservicios.app.cursos.services;

import com.formacionbdi.microservicios.app.cursos.models.entity.Course;
import com.formacionbdi.microservicios.commons.services.CommonService;

public interface CourseService extends CommonService<Course> {

    public Course findCourseByStudentId(Long id);

}
