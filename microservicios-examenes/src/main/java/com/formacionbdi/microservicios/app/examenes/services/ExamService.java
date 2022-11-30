package com.formacionbdi.microservicios.app.examenes.services;


import com.formacionbdi.microservicios.commons.models.entity.Exam;
import com.formacionbdi.microservicios.commons.models.entity.Subject;
import com.formacionbdi.microservicios.commons.services.CommonService;

import java.util.List;

public interface ExamService extends CommonService<Exam> {
    public List<Exam> findExamByName(String term);

    public Iterable<Subject> findAllSubjects();
}
