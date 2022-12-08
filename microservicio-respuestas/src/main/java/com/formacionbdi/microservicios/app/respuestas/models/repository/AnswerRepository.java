package com.formacionbdi.microservicios.app.respuestas.models.repository;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {

    @Query("{'studentId' : ?0, 'questionId' : {$in: ?1}}")
    public Iterable<Answer> findAnswerByStudentByQuestionIds(Long studentId, Iterable<Long> questionIds);

    /*@Query("select a from Answer a  join fetch a.question q join fetch q.exam e where a.studentId =?1 and  e.id =?2")
    public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId);

    @Query("select e.id from Answer a join a.question q join q.exam e where a.studentId = ?1 group by e.id")
    public Iterable<Long> findExamsIdsWithAnswerByStudentId(Long studentId);*/
}
