package com.formacionbdi.microservicios.app.usuarios.services;


import com.formacionbdi.microservicios.app.usuarios.client.CourseFeignClient;
import com.formacionbdi.microservicios.app.usuarios.repositories.StudentRepository;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl extends CommonServiceImpl<Student, StudentRepository> implements StudentService{


    @Autowired
    private CourseFeignClient client;

    @Override
    public List<Student> findByNameOrLastName(String term) {
        return repository.findByNameOrLastName(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Student> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void deleteCourseStudentById(Long id) {
        client.deleteCourseStudentById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
        this.deleteCourseStudentById(id);
    }
}
