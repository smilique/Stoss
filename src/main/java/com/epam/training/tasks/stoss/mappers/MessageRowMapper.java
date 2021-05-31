package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.Message;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import static com.epam.training.tasks.stoss.entities.Message.*;

public class MessageRowMapper implements RowMapper<Message>{
    @Override
    public Message map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);
        String text = resultSet.getString(MESSAGE);
        String username = resultSet.getString(USER_NAME);
        Date date = resultSet.getDate(TIME);
        Time time = resultSet.getTime(TIME);

        return new Message(id, text, userId, username, time, date);
    }
}
