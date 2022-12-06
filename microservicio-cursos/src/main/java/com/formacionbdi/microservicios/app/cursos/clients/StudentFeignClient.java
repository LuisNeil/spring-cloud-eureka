package com.formacionbdi.microservicios.app.cursos.clients;

import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservicio-usuarios")
public interface StudentFeignClient {

    @GetMapping("/students-by-course")
    public Iterable<Student> getStudentsByCourse(@RequestParam List<Long> ids);
}
