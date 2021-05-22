package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.Message;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class MessageRowMapper implements RowMapper<Message>{
    @Override
    public Message map(ResultSet resultSet) throws SQLException {
        String messageId = Message.ID;
        Long id = resultSet.getLong(messageId);
        String messageText = Message.MESSAGE;
        String message = resultSet.getString(messageText);
        String messageUser = Message.USER_NAME;
        String username = resultSet.getString(messageUser);
        String messageDate = Message.TIME;
        Date date = resultSet.getDate(messageDate);
        String messageTime = Message.TIME;
        Time time = resultSet.getTime(messageTime);

        return new Message(id, message, username, time, date);
    }
}
