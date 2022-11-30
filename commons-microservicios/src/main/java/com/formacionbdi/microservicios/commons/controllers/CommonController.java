package com.formacionbdi.microservicios.commons.controllers;


import com.formacionbdi.microservicios.commons.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonController<E, S extends CommonService<E>> {

    @Autowired
    protected S service;

    @GetMapping("/")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<E> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(o.get());
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody E entity, BindingResult result){
        if(result.hasErrors()){
            return this.validate(result);
        }
        E entityDb = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> validate(BindingResult result){
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(err-> errors.put(err.getField(),
                "The field " + err.getField() +" " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);

    }
}
