package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.connections.ConnectionException;
import com.epam.training.tasks.stoss.dao.DaoException;
import com.epam.training.tasks.stoss.dao.DaoHelper;
import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class GameService {

    private static final Logger LOGGER = Logger.getLogger(GameService.class);

    private final DaoHelperFactory daoHelperFactory;

    public GameService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void win(Long userId, Long bet) throws ServiceException{
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();

        } catch (ConnectionException | IOException | SQLException | DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }


}
