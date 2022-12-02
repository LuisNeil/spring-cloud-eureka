package com.formacionbdi.microservicios.app.respuestas.models.entity;

import com.formacionbdi.microservicios.commons.models.entity.Exam;
import com.formacionbdi.microservicios.commons.models.entity.Question;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(fetch = FetchType.LAZY)
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
