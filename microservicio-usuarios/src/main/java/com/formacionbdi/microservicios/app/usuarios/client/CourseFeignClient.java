package com.formacionbdi.microservicios.app.usuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-cursos")
public interface CourseFeignClient {
    @DeleteMapping("/delete-student/{id}")
    public void deleteCourseStudentById(@PathVariable Long id);

}
