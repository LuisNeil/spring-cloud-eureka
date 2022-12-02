package com.formacionbdi.microservicios.app.respuestas.controllers;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Answer;
import com.formacionbdi.microservicios.app.respuestas.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Iterable<Answer> answers){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAll(answers));
    }

    @GetMapping("/student/{studentId}/exam/{examId}")
    public ResponseEntity<?> findAnswerByStudentByExam(@PathVariable Long studentId, @PathVariable Long examId){
        return ResponseEntity.ok(service.findAnswerByStudentByExam(studentId,examId));
    }

    @GetMapping("/student/{studentId}/examsId-answered")
    public ResponseEntity<?> findExamsIdsWithAnswerByStudentId(@PathVariable Long studentId){
        return ResponseEntity.ok(service.findExamsIdsWithAnswerByStudentId(studentId));
    }



}
