package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import com.epam.training.tasks.stoss.entities.Message;
import com.epam.training.tasks.stoss.mappers.MessageRowMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MessageDao extends AbstractDao<Message> {

    private static final String POST_QUERY = "insert into chat set message = ?, " +
            "user_id = ?, time = ?;";
    private static final String GET_ALL_QUERY = "select chat.id, chat.message, chat.time, user.name " +
            "from chat inner join user on chat.user_id = user.id order by chat.id";

    protected MessageDao(ProxyConnection connection) {
        super(connection, new MessageRowMapper());
    }

    public void post(String message, Long userId) throws DaoException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        executeUpdate(POST_QUERY, message, userId, date);
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
        return Optional.empty();
    }

    @Override
    public void save(Message item) throws DaoException {

    }

    @Override
    public void removeById(Long id) {

    }
}
