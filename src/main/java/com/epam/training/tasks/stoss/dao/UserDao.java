package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.mappers.RowMapper;
import com.epam.training.tasks.stoss.mappers.UserRowMapper;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserDao extends AbstractDao<User>{

    private final static Logger LOGGER = Logger.getLogger(UserDao.class);

    private final static String UPDATE_QUERY = "update";
    private static final String CREATE_QUERY = "create";
    private final static String LOGIN_QUERY = "SELECT * FROM USER WHERE LOGIN = '?' AND PASSWORD = MD5('?')";


    protected UserDao(ProxyConnection connection) {
        super(connection, new UserRowMapper());
    }


    public Optional<User> findUserByLoginAndPassword (String login, String password) throws DaoException {
        LOGGER.debug("finging user with login: " + login);
        return executeForSingleResult(LOGIN_QUERY, login, password);
    }


    @Override
    protected String getTableName() {
        return User.TABLE;
    }

    @Override
    public Optional<User> findById(Long id) {
        //return user by id
        return Optional.empty();
    }

    @Override
    public void save(User user) throws DaoException {
        //save user into db
        if (user.getId() == null) {
            create(user);
        } else {
            update(user);
        }
    }

    private void create(User user) throws DaoException {
        Long id = null;
//        String login = user.getLogin;
//        String password = user.getPassword;
        String name = user.getName();
        executeUpdate(CREATE_QUERY,user);
    }

    private void update(User user) throws DaoException {
        Optional<User> optionalUser = findById(user.getId());
        executeUpdate(UPDATE_QUERY, optionalUser);
    }

    @Override
    public void removeById(Long id) {
        //remove user from db
    }

}
