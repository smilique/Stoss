package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.entities.Entity;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents a pattern for interacting with database
 *
 * @author Anton Tomashevich
 * @see com.epam.training.tasks.stoss.dao.AbstractDao
 * @see com.epam.training.tasks.stoss.mappers.RowMapper
 * @param <T>
 */

public interface Dao <T extends Entity> {

    /**
     * The method for getting single instance of entity from db
     *
     * @param id identifier of entity
     * @return Optional<T>
     * @throws DaoException
     */
    Optional<T> findById(Long id) throws DaoException;

    /**
     * The method for getting from multiple entities from db
     *
     * @return List<T> list of entities
     * @throws DaoException
     */
    List<T> getAll() throws DaoException;

    /**
     * The method for inserting single entity data in db
     *
     * @param item Entity to insert into db {@link Entity}
     * @throws DaoException
     */
    void save(T item) throws DaoException;

    /**
     * The method for removing single entry in db
     *
     * @param id identifier of entity
     * @throws DaoException
     */
    void removeById(Long id) throws DaoException;
}
