package com.formacionbdi.microservicios.app.usuarios.repositories;

import com.formacionbdi.microservicios.commons.students.models.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    @Query("select s from  Student s where s.name like %?1% or s.lastname like %?1%")
    public List<Student> findByNameOrLastName(String term);
}
