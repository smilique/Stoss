package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final static Logger LOGGER = Logger.getLogger(DaoHelper.class);

    private final ProxyConnection connection;
    //private final RowMapper<User> mapper;

    public DaoHelper(ProxyConnection connection) {
        LOGGER.debug("Daohelper with connection: " + connection);
        this.connection = connection;
    }

    public UserDao createUserDao() {
        UserDao userDao = new UserDao(connection);
        LOGGER.debug("UserDao created: " + userDao);
        return userDao;
    }

    public NewsDao createNewsDao() {
        NewsDao newsDao = new NewsDao(connection);
        LOGGER.debug("NewsDao created: " + newsDao);
        return newsDao;
    }

    public MessageDao createMessageDao() {
        MessageDao messageDao = new MessageDao(connection);
        return messageDao;
    }

    //create another dao

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
            LOGGER.debug("Transaction started");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            LOGGER.debug("Transaction ended");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        LOGGER.debug("Closing connection " + connection);
        connection.close();
    }


}
