package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final static Logger LOGGER = Logger.getLogger(DaoHelper.class);

    private final ProxyConnection connection;

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public UserDao createUserDao() {
        return new UserDao(connection);
    }

    public NewsDao createNewsDao() {
        return new NewsDao(connection);
    }

    public MessageDao createMessageDao() {
        return new MessageDao(connection);
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
            LOGGER.debug("Transaction started");
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            LOGGER.debug("Transaction ended");
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            LOGGER.debug("Closing connection " + connection);
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }


    }


}
