package com.formacionbdi.microservicios.app.respuestas.services;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Answer;

public interface AnswerService {
    public Iterable<Answer> saveAll(Iterable<Answer> answers);

    public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId);

    public Iterable<Long> findExamsIdsWithAnswerByStudentId(Long studentId);


}
