package com.formacionbdi.microservicios.app.examenes.services;


import com.formacionbdi.microservicios.app.examenes.repositories.ExamRepository;
import com.formacionbdi.microservicios.app.examenes.repositories.SubjectRepository;
import com.formacionbdi.microservicios.commons.models.entity.Exam;
import com.formacionbdi.microservicios.commons.models.entity.Subject;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Exam, ExamRepository> implements ExamService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Exam> findExamByName(String term) {
        return repository.findExamByName(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Iterable<Long> findExamsIdsWithAnswersByQuestionIds(Iterable<Long> questionIds) {
        return repository.findExamsIdsWithAnswersByQuestionIds(questionIds);
    }
}
