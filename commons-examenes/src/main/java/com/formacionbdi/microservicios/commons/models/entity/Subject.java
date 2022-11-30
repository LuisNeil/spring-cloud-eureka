package com.formacionbdi.microservicios.commons.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnoreProperties(value = {"children"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject parent;

    @JsonIgnoreProperties(value = {"parent"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Subject> childrem;

    public Subject() {
        this.childrem = new ArrayList<>();
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

    public Subject getParent() {
        return parent;
    }

    public void setParent(Subject parent) {
        this.parent = parent;
    }

    public List<Subject> getChildrem() {
        return childrem;
    }

    public void setChildrem(List<Subject> childrem) {
        this.childrem = childrem;
    }
}
