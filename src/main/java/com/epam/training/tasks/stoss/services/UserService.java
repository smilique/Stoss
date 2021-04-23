package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.connections.ConnectionException;
import com.epam.training.tasks.stoss.dao.DaoException;
import com.epam.training.tasks.stoss.dao.DaoHelper;
import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.dao.UserDao;
import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final static Logger LOGGER = Logger.getLogger(UserService.class);

    private final DaoHelperFactory daoHelperFactory;

    public UserService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public Optional<User> login(String login, String password) throws Exception {

        try (DaoHelper helper = daoHelperFactory.create()) {
            LOGGER.debug("Helper: " + helper);
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> user = userDao.findUserByLoginAndPassword(login, password);
            helper.endTransaction();
            LOGGER.debug("Returning user: " + user);
            return user;
        } catch (DaoException e) {
            LOGGER.debug("Exception " + e);
//            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public Optional<User> register(String login, String password, String name) throws Exception {

        try (DaoHelper helper = daoHelperFactory.create()) {
            LOGGER.debug("Helper: " + helper);
            UserDao userDao = helper.createUserDao();
            Optional<User> existingUser = userDao.findUserByLogin(login);
            if (!existingUser.isPresent()) {
                userDao.addNewUser(login, password, name);
            }
            return existingUser;
        } catch (DaoException e) {
            LOGGER.debug("Exception " + e);
            throw new ServiceException(e);
        }
    }

    public boolean validateLogin(String login) throws Exception {
        Optional<User> user = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            user = userDao.findUserByLogin(login);
            helper.endTransaction();
            LOGGER.debug("user exists? " + user.isPresent());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return (!user.isPresent());
    }


}
