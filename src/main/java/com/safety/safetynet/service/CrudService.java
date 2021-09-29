package com.safety.safetynet.service;

import java.util.Optional;

public interface CrudService<T> {

    Optional<T> findById(long id);

    T insert(T t);

    void delete(long id);

    T update(long id, T t);

    void deleteByName(String firstName, String lastName);

}
