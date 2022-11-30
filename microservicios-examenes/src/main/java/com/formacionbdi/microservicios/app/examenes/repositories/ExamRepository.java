package com.formacionbdi.microservicios.app.examenes.repositories;


import com.formacionbdi.microservicios.commons.models.entity.Exam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExamRepository extends CrudRepository<Exam, Long> {

    @Query("select e from Exam e where e.name like %?1")
    public List<Exam> findExamByName(String term);
}
