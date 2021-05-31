package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.dao.*;
import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final static Logger LOGGER = Logger.getLogger(UserService.class);

    private final DaoHelperFactory daoHelperFactory;

    public UserService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> user = userDao.findUserByLoginAndPassword(login, password);
            helper.endTransaction();
            return user;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<User> register(String login, String password, String name) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> existingUser = userDao.findUserByLogin(login);
            if (!existingUser.isPresent()) {
                User user = new User(login, name, password);
                userDao.save(user);
            }
            helper.endTransaction();
            return existingUser;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    public boolean validateLogin(String login) throws ServiceException {
        Optional<User> user = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            user = userDao.findUserByLogin(login);
            helper.endTransaction();
            LOGGER.debug("user exists? " + user.isPresent());
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return user.isPresent();
    }

    public void deleteUser(String login) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            userDao.deleteUser(login);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<User> deposit(Long userId, BigDecimal deposit) throws ServiceException {
        Optional<User> updatedUser = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> optionalUser = userDao.findById(userId);
            User user = optionalUser.get();
            BigDecimal balance = user.getBalance();
            BigDecimal newBalance = balance.add(deposit);
            LOGGER.debug("changing balance from: " + balance + " to: " + newBalance);
            userDao.updateBalance(userId, newBalance);
            updatedUser = userDao.findById(userId);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return updatedUser;
    }

    public void changePassword(Long userId, String password) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            userDao.updatePassword(userId, password);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<User> updateUserpic(Long userId, String path) throws ServiceException {
        Optional<User> user = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            userDao.updateUserpic(userId, path);
            user = userDao.findById(userId);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    public Optional<User> updatePoints(Long userId, Long points) throws ServiceException {
        Optional<User> user = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            userDao.updatePoints(userId,points);
            user = userDao.findById(userId);
            helper.endTransaction();
        } catch (DaoException e) {
           LOGGER.error(e);
           throw new ServiceException(e);
        }
        return user;
    }

    public Optional<User> getInfo(String login) throws ServiceException {
        Optional<User> optionalUser = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            optionalUser = userDao.findUserByLogin(login);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    public Optional<User> changeLocale(Long userId, String languageTag) throws ServiceException {
        Optional<User> optionalUser = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            userDao.updateLocale(userId,languageTag);
            optionalUser = userDao.findById(userId);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    public List<User> getAllUsers() throws ServiceException {
        List<User> users = new ArrayList<>();
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            users = userDao.getAll();
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    public List<User> getRating() throws ServiceException {
        List<User> users = new ArrayList<>();
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            users = userDao.getByPoints();
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

}
