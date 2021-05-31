package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import com.epam.training.tasks.stoss.entities.Message;
import com.epam.training.tasks.stoss.mappers.MessageRowMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MessageDao extends AbstractDao<Message> {

    private static final String POST_QUERY = "insert into chat set message = ?, " +
            "user_id = ?, time = ?;";
    private static final String DELETE_QUERY = "delete from chat where id = ?";
    private static final String GET_ALL_QUERY = "select chat.id, chat.message, chat.time, user.name, user.id " +
            "from chat inner join user on chat.user_id = user.id order by chat.id desc limit 20";
    private static final String GET_BY_ID_QUERY = "select chat.id, chat.message, chat.time, user.name, user.id from chat inner join user " +
            "on chat.user_id = user.id where chat.id = ?";

    protected MessageDao(ProxyConnection connection) {
        super(connection, new MessageRowMapper());
    }

    public void delete(Long messageId) throws DaoException {
        executeUpdate(DELETE_QUERY, messageId);
    }

    @Override
    public List<Message> getAll() throws DaoException {
        return executeQuery(GET_ALL_QUERY);
    }

    @Override
    protected String getTableName() {
        return Message.TABLE;
    }

    @Override
    public Optional<Message> findById(Long id) throws DaoException {
        return executeForSingleResult(GET_BY_ID_QUERY, id);
    }

    @Override
    public void save(Message item) throws DaoException {
        String text = item.getText();
        Long userId = item.getUserId();
        Date date = item.getDate();
        executeUpdate(POST_QUERY, text, userId, date);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(DELETE_QUERY, id);
    }
}
