package com.daojpa.model;

public interface Dao<E,K> {
    void persist(E entity);
    void update(E entity);
    void remove(K id);
    E findById(K id);
}