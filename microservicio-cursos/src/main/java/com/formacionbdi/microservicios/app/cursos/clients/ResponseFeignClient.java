package com.formacionbdi.microservicios.app.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-respuestas")
public interface ResponseFeignClient {
    @GetMapping("/student/{studentId}/examsId-answered")
    public Iterable<Long> findExamsIdsWithAnswerByStudentId(@PathVariable Long studentId);
    
    }
