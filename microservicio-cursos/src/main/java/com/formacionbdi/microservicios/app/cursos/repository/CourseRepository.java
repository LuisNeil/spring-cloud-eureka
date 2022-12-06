package com.formacionbdi.microservicios.app.cursos.repository;

import com.formacionbdi.microservicios.app.cursos.models.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @Query("select c from Course c join fetch c.courseStudents cs where cs.studentId =?1")
    public Course findCourseByStudentId(Long id);
}
