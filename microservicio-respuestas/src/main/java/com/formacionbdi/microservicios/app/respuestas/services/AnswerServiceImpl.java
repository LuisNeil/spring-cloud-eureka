package com.formacionbdi.microservicios.app.respuestas.services;

import com.formacionbdi.microservicios.app.respuestas.clients.ExamFeignClient;
import com.formacionbdi.microservicios.app.respuestas.models.entity.Answer;
import com.formacionbdi.microservicios.app.respuestas.models.repository.AnswerRepository;
import com.formacionbdi.microservicios.commons.models.entity.Exam;
import com.formacionbdi.microservicios.commons.models.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private final AnswerRepository repository;

    @Autowired
    private ExamFeignClient client;

    @Autowired
    public AnswerServiceImpl(AnswerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Answer> saveAll(Iterable<Answer> answers) {
        return repository.saveAll(answers);
    }

    @Override
    public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId) {
       //return repository.findAnswerByStudentByExam(studentId, examId);
        Exam exam = client.getExamById(examId);
        List<Question> questions = exam.getQuestions();
        List<Long> questionsIds = questions.stream().map(Question::getId).collect(Collectors.toList());
        List<Answer> answers = (List<Answer>) repository.findAnswerByStudentByQuestionIds(studentId, questionsIds);
        answers = answers.stream().peek(a -> questions.forEach(q -> {
            if(q.getId().equals(a.getQuestionId())){
                a.setQuestion(q);
            }
        })).collect(Collectors.toList());
        return answers;
    }

    @Override
    public Iterable<Long> findExamsIdsWithAnswerByStudentId(Long studentId) {
        //return repository.findExamsIdsWithAnswerByStudentId(studentId);
        return null;
    }
}
