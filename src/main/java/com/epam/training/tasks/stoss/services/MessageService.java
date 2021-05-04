package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.connections.ConnectionException;
import com.epam.training.tasks.stoss.dao.DaoException;
import com.epam.training.tasks.stoss.dao.DaoHelper;
import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.dao.MessageDao;
import com.epam.training.tasks.stoss.entities.Message;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private static final Logger LOGGER = Logger.getLogger(MessageService.class);

    private final DaoHelperFactory daoHelperFactory;

    public MessageService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Message> getMessages() throws ServiceException {
        List<Message> messages = new ArrayList<>();
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            MessageDao messageDao = helper.createMessageDao();
            messages = messageDao.getAll();
            helper.endTransaction();
        } catch (ConnectionException | IOException | SQLException | DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return messages;
    }

    public void postMessage(String messageText, Long userId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            MessageDao messageDao = helper.createMessageDao();
            messageDao.post(messageText, userId);
            helper.endTransaction();
        } catch (ConnectionException | IOException | SQLException | DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
