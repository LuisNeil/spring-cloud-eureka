package com.formacionbdi.microservicios.commons.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CommonService<E> {
    public Iterable<E> findAll();
    public Page<E> findAll(Pageable pageable);
    public Optional<E> findById(Long id);
    public E save(E student);
    public void deleteById(Long id);
}
