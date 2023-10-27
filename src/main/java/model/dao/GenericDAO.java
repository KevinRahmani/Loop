package model.dao;

import java.util.List;
public interface GenericDAO<T> {

    T create(T entity);
    T findById(int id);
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> findByFilters(ProductSearchDTO filters);
}
