package com.bogdan.library.dao;

import java.util.List;
import java.util.Optional;


public interface Dao<T> {
    Optional<T> getById(int id);
    List<T> getAll();
    void save(T t);
    void update(int id,T t);
    int delete(int id);
}
