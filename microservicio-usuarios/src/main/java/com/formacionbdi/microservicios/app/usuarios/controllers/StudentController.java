package com.formacionbdi.microservicios.app.usuarios.controllers;


import com.formacionbdi.microservicios.app.usuarios.services.StudentService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StudentController extends CommonController<Student, StudentService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Student student, @PathVariable Long id){
        Optional<Student> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Student studentDb = o.get();
        studentDb.setName(student.getName());
        studentDb.setLastname(student.getLastname());
        studentDb.setEmail(student.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDb));
    }

    @GetMapping("/filter/{term}")
    public ResponseEntity<?> filter(@PathVariable String term){
        return ResponseEntity.ok(service.findByNameOrLastName(term));
    }

}
