package model.dao;

import java.util.List;
public interface GenericDAO<T> {

    T findById(int id);
    void add(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}
