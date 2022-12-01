package com.formacionbdi.microservicios.app.usuarios.controllers;


import com.formacionbdi.microservicios.app.usuarios.services.StudentService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
public class StudentController extends CommonController<Student, StudentService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody Student student, BindingResult result, @PathVariable Long id ){
        if(result.hasErrors()){
            return this.validate(result);
        }
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


    @PostMapping("/create-with-picture")
    public ResponseEntity<?> createWithPicture(@Valid Student student,
                                               BindingResult result,
                                               @RequestParam MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            student.setPhoto(file.getBytes());
        }
        return super.create(student, result);
    }

    @PutMapping("/edit-with-picture/{id}")
    public ResponseEntity<?> editWithPicture(@Valid Student student,
                                             BindingResult result,
                                             @PathVariable Long id,
                                             @RequestParam MultipartFile file ) throws IOException {
        if(result.hasErrors()){
            return this.validate(result);
        }
        Optional<Student> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Student studentDb = o.get();
        studentDb.setName(student.getName());
        studentDb.setLastname(student.getLastname());
        studentDb.setEmail(student.getEmail());

        if(!file.isEmpty()){
            studentDb.setPhoto(file.getBytes());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDb));
    }

    @GetMapping("/upload/img/{id}")
    public ResponseEntity<?> seePhoto(@PathVariable Long id){
        Optional<Student> o = service.findById(id);
        if(o.isEmpty() || o.get().getPhoto() == null){
            return ResponseEntity.notFound().build();
        }

        Resource img = new ByteArrayResource(o.get().getPhoto());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
    }
}
