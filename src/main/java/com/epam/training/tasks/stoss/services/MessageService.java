package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.dao.DaoException;
import com.epam.training.tasks.stoss.dao.DaoHelper;
import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.dao.MessageDao;
import com.epam.training.tasks.stoss.entities.Message;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private static final Logger LOGGER = Logger.getLogger(MessageService.class);

    private final DaoHelperFactory daoHelperFactory;

    public MessageService() {
        this.daoHelperFactory = new DaoHelperFactory();
    }

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
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return messages;
    }

    public void postMessage(String messageText, Long userId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            MessageDao messageDao = helper.createMessageDao();
            Message message = new Message(messageText, userId);
            messageDao.save(message);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    public void deleteMessage(Long messageId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            MessageDao messageDao = helper.createMessageDao();
            messageDao.removeById(messageId);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
