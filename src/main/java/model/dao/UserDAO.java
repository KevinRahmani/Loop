package model.dao;

public interface UserDAO<T> extends GenericDAO<T>{

    T connect(String email, String password);


}
