package com.formacionbdi.microservicios.app.examenes.controllers;


import com.formacionbdi.microservicios.app.examenes.services.ExamService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.models.entity.Exam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExamController extends CommonController<Exam, ExamService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Exam exam, @PathVariable Long id){

        Optional<Exam> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Exam examDb = o.get();

        examDb.setName(exam.getName());

        examDb.getQuestions()
                .stream()
                .filter(qdb -> !exam.getQuestions().contains(qdb))
                .forEach(examDb::removeQuestion);

        examDb.setQuestions(exam.getQuestions());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examDb));
    }

    @GetMapping("filter/{term}")
    public ResponseEntity<?> filter(@PathVariable String term){
        return ResponseEntity.ok(service.findExamByName(term));
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> listSubjects(){
        return ResponseEntity.ok(service.findAllSubjects());
    }

}
