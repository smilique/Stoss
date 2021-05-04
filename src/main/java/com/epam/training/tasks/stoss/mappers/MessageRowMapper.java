package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.Message;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class MessageRowMapper implements RowMapper<Message>{
    @Override
    public Message map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Message.ID);
        String message = resultSet.getString(Message.MESSAGE);
        String username = resultSet.getString(Message.USER_NAME);
        Date date = resultSet.getDate(Message.TIME);
        Time time = resultSet.getTime(Message.TIME);

        return new Message(id, message, username, time, date);
    }
}
