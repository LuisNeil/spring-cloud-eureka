package com.formacionbdi.microservicios.commons.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createAt;

    @JsonIgnoreProperties(value = {"exam"}, allowSetters = true)
    @OneToMany(mappedBy = "exam",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public Exam() {
        this.questions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setQuestions(List<Question> questions) {
        this.questions.clear();
       questions.forEach(this::addQuestion);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
        question.setExam(this);
    }


    public void removeQuestion(Question question) {
        this.questions.remove(question);
        question.setExam(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exam)) return false;

        Exam e = (Exam) o;
        return this.id != null && this.id.equals(e.getId());
    }
}
