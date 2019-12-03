package com.test.management.system.service;

import java.util.SortedSet;

public interface CrudService<T, ID> {

    SortedSet<T> findAll();

    T findById(ID id);

    T save(T object);

    void deleteById(ID id);
}