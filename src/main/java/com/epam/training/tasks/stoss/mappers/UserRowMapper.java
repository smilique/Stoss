package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    private final static Logger LOGGER = Logger.getLogger(UserRowMapper.class);

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID);
        String name = resultSet.getString(User.NAME);
        Long points = resultSet.getLong(User.POINTS);
        BigDecimal balance = BigDecimal.valueOf(resultSet.getLong(User.BALANCE));
        String role = resultSet.getString(User.ROLE);
        String locale = resultSet.getString(User.LOCALE);
        String userpic = resultSet.getString(User.PICTURE);

        LOGGER.debug("user id: " + id);

        return new User(id, name, balance, points, role, locale, userpic);
    }
}
