package com.formacionbdi.microservicios.commons.services;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CommonService<E> {
    public Iterable<E> findAll();
    public Optional<E> findById(Long id);
    public E save(E student);
    public void deleteById(Long id);
}
