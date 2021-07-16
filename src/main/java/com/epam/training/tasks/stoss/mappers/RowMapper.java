package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.Entity;
import com.epam.training.tasks.stoss.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The interface represents a pattern for creating Entity
 * from provided data
 *
 * @author Anton Tomashevich
 * @param <T>
 * @see com.epam.training.tasks.stoss.entities.Entity
 */
public interface RowMapper<T extends Entity> {

    /**
     * The method used for generating entity based
     * on provided ResultSet {@link ResultSet}
     *
     * @param resultSet - result of SQL query
     * @return T - successor of Entity {@link Entity}
     * @throws SQLException
     */
    T map(ResultSet resultSet) throws SQLException;

}
