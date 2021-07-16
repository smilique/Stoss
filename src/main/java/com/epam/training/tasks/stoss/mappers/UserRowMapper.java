package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.training.tasks.stoss.entities.User.*;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString(LOGIN);
        Long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        Long points = resultSet.getLong(POINTS);
        BigDecimal balance = resultSet.getBigDecimal(BALANCE);
        String role = resultSet.getString(ROLE);
        String locale = resultSet.getString(LOCALE);
        String userpic = resultSet.getString(PICTURE);
        String password = resultSet.getString(PASSWORD);

        return new User(login, id, name, balance, points, role, locale, userpic, password);
    }
}
