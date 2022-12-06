package com.formacionbdi.microservicios.app.cursos.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.formacionbdi.microservicios.commons.models.entity.Exam;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    @JsonIgnoreProperties(value = {"course"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseStudent> courseStudents;

    //@OneToMany(fetch = FetchType.LAZY)
    @Transient
    private List<Student> students;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Exam> exams;

    public Course() {
        this.students = new ArrayList<>();
        this.exams = new ArrayList<>();
        this.courseStudents = new ArrayList<>();
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<CourseStudent> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void deleteStudent(Student student) {
        this.students.remove(student);
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void addExam(Exam exam) {
        this.exams.add(exam);
    }

    public void removeExam(Exam exam) {
        this.exams.remove(exam);
    }

    public void addCourseStudents(CourseStudent courseStudent) {
        this.courseStudents.add(courseStudent);
    }

    public void removeCourseStudents(CourseStudent courseStudent) {
        this.courseStudents.remove(courseStudent);
    }
}
