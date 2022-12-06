package com.formacionbdi.microservicios.app.cursos.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.formacionbdi.microservicios.commons.students.models.entity.Student;

import javax.persistence.*;

@Entity
@Table(name = "course_students")
public class CourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", unique = true)
    private Long studentId;

    @JsonIgnoreProperties(value = {"courseStudents"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseStudent)) return false;

        CourseStudent cs = (CourseStudent) o;
        return this.studentId != null && this.studentId.equals(cs.getStudentId());
    }
}
