package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ConnectionException;
import com.epam.training.tasks.stoss.connections.ConnectionPool;
import org.apache.log4j.Logger;

import java.io.IOException;

public class DaoHelperFactory {

    private static final Logger LOGGER = Logger.getLogger(DaoHelperFactory.class);

    public DaoHelper create() throws DaoException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            LOGGER.debug("Got connectionPool: " + connectionPool);
            return new DaoHelper(connectionPool.getConnection());
        } catch (ConnectionException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }
}
