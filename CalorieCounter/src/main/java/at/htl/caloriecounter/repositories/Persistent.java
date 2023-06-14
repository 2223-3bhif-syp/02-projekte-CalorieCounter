package at.htl.caloriecounter.repositories;

import java.util.List;

public interface Persistent<T> {
    void save(T entity);
    void update(T entity);
    void insert(T entity);
    void delete(long id);
    List<T> findAll();
    T findById(long id);
}
