package com.formacionbdi.microservicios.app.examenes.controllers;


import com.formacionbdi.microservicios.app.examenes.services.ExamService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.models.entity.Exam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ExamController extends CommonController<Exam, ExamService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody Exam exam, BindingResult result, @PathVariable Long id){

        if(result.hasErrors())
            return this.validate(result);

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

    @GetMapping("/answered-by-questions")
    public ResponseEntity<?> findExamsIdsWithAnswersByQuestionIdAnswered(@RequestParam List<Long> questionIds){
        Iterable<Long> ids = service.findExamsIdsWithAnswersByQuestionIds(questionIds);
        return ResponseEntity.ok().body(ids);
    }


}
