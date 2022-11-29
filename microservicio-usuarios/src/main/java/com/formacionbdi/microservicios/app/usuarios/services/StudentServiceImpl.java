package com.formacionbdi.microservicios.app.usuarios.services;


import com.formacionbdi.microservicios.app.usuarios.repositories.StudentRepository;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends CommonServiceImpl<Student, StudentRepository> implements StudentService{


    @Override
    public List<Student> findByNameOrLastName(String term) {
        return repository.findByNameOrLastName(term);
    }
}
