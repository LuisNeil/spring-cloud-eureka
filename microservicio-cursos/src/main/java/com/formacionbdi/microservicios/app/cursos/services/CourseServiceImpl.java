package com.formacionbdi.microservicios.app.cursos.services;

import com.formacionbdi.microservicios.app.cursos.clients.ResponseFeignClient;
import com.formacionbdi.microservicios.app.cursos.clients.StudentFeignClient;
import com.formacionbdi.microservicios.app.cursos.models.entity.Course;
import com.formacionbdi.microservicios.app.cursos.repository.CourseRepository;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {

    @Autowired
    private ResponseFeignClient client;

    @Autowired
    private StudentFeignClient studentClient;

    @Override
    @Transactional(readOnly = true)
    public Course findCourseByStudentId(Long id) {
        return repository.findCourseByStudentId(id);
    }

    @Override
    public Iterable<Long> findExamsIdsWithAnswerByStudentId(Long studentId) {
        return client.findExamsIdsWithAnswerByStudentId(studentId);
    }

    @Override
    public Iterable<Student> getStudentsByCourse(List<Long> ids) {
        return studentClient.getStudentsByCourse(ids);
    }
}
