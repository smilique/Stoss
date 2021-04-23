package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.Entity;
import com.epam.training.tasks.stoss.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Entity> {

    T map(ResultSet resultSet) throws SQLException;

}
