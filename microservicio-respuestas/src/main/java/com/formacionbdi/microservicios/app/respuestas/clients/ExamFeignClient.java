package com.formacionbdi.microservicios.app.respuestas.clients;

import com.formacionbdi.microservicios.commons.models.entity.Exam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-examenes")
public interface ExamFeignClient {

    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable Long id);
}
