package com.formacionbdi.microservicios.app.usuarios.services;


import com.formacionbdi.microservicios.commons.services.CommonService;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;

import java.util.List;


public interface StudentService extends CommonService<Student> {

    public List<Student> findByNameOrLastName(String term);

    public Iterable<Student> findAllById(Iterable<Long> ids);
}
