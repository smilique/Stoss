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
//        Long id = resultSet.getLong(1);
        String login = resultSet.getString(User.LOGIN);
        String password = resultSet.getString(User.PASSWORD);
        String name = resultSet.getString(User.NAME);
//        String name = resultSet.getString(2);

        LOGGER.debug(resultSet);
        //UserRole
        return new User(id,name);
    }
}
