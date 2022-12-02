package com.formacionbdi.microservicios.app.respuestas.services;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Answer;
import com.formacionbdi.microservicios.app.respuestas.models.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository repository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Iterable<Answer> saveAll(Iterable<Answer> answers) {
        return repository.saveAll(answers);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId) {
        return repository.findAnswerByStudentByExam(studentId, examId);
    }

    @Override
    @Transactional
    public Iterable<Long> findExamsIdsWithAnswerByStudentId(Long studentId) {
        return repository.findExamsIdsWithAnswerByStudentId(studentId);
    }
}
