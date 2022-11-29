package com.formacionbdi.microservicios.app.cursos.services;

import com.formacionbdi.microservicios.app.cursos.models.entity.Course;
import com.formacionbdi.microservicios.app.cursos.repository.CourseRepository;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {
    @Override
    @Transactional(readOnly = true)
    public Course findCourseByStudentId(Long id) {
        return repository.findCourseByStudentId(id);
    }
}
