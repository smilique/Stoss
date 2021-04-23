package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    private final static Logger LOGGER = Logger.getLogger(UserRowMapper.class);

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID);
        String name = resultSet.getString(User.NAME);
        Long points = resultSet.getLong(User.POINTS);
        Long balance = resultSet.getLong(User.BALANCE);
        String role = resultSet.getString(User.ROLE);

        LOGGER.debug(resultSet.getLong(User.ID));
        //UserRole
//        return new User(id,name);
        return new User(id, name, balance, points, role);
    }
}
