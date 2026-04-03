package dao;

import java.util.List;

public interface DAOInterface<T, ID> {

    List<T> findAll();

    T findById(ID id);

    void create(T t);

    void update(T t);

    boolean deleteById(ID id);
}