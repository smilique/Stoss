package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.entities.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao <T extends Entity> {

    Optional<T> findById(Long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void save(T item) throws DaoException;

    void removeById(Long id);
}
