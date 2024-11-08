package com.example.demo1.dao;

import java.util.List;

public interface DAOInterface<S> {

    List<S> getAll();
    boolean create(S s);
    boolean update(S s);
    <K> void delete(K s);
    <K> S find(K id);

}
