package com.formacionbdi.microservicios.app.cursos.repository;

import com.formacionbdi.microservicios.app.cursos.models.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("select c from Course c join fetch c.students s where s.id =?1")
    public Course findCourseByStudentId(Long id);
}
