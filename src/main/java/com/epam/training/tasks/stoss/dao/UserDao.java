package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.mappers.UserRowMapper;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserDao extends AbstractDao<User>{

    private final static Logger LOGGER = Logger.getLogger(UserDao.class);

    private static final String USER_PARAMS = "ROLE_ID = 2, points = 0, balance = 0";
    private static final String UPDATE_QUERY = "update";
    private static final String ADD_NEW_QUERY = "insert into user set login = ?, password = md5(?), name = ?, " + USER_PARAMS;
    private static final String LOGIN_QUERY = "SELECT user.id, user.balance, user.name, user.points, r.name from user inner join role r on user.ROLE_ID = r.ID where login = ? and password = MD5(?)";
    private static final String VALIDATE_QUERY = "select user.id from user where login = ?";



    protected UserDao(ProxyConnection connection) {
        super(connection, new UserRowMapper());
    }


    public Optional<User> findUserByLoginAndPassword (String login, String password) throws DaoException {
        LOGGER.debug("checking password for: " + login);
        return executeForSingleResult(LOGIN_QUERY, login, password);
    }

    public Optional<User> findUserByLogin (String login) throws DaoException {
        LOGGER.debug("finging user with login: " + login);
        return executeForSingleResult(VALIDATE_QUERY, login);
    }

    public void addNewUser (String login, String password, String name) throws DaoException {
            LOGGER.debug("adding new user");
            executeUpdate(ADD_NEW_QUERY, login, password, name);
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
        executeUpdate(ADD_NEW_QUERY,user);
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
