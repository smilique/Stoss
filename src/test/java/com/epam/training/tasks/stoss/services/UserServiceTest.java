package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.dao.DaoException;
import com.epam.training.tasks.stoss.dao.DaoHelper;
import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.dao.UserDao;
import com.epam.training.tasks.stoss.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private static UserService userService;
    private static UserDao userDao;

    private final User firstUser = new User("login", 1L, "User", BigDecimal.ONE, 0L, "user",
            "en", "", "password");
    private final User secondUser = new User("login2", 2L, "User2", BigDecimal.ONE, 0L, "user",
            "en", "", "password2");
    private List<User> usersList = new ArrayList<>();

    @BeforeClass
    public static void beforeClass() throws Exception {
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        when(daoHelperFactory.create())
                .thenReturn(daoHelper);
        userDao = Mockito.mock(UserDao.class);
        when(daoHelper.createUserDao())
                .thenReturn(userDao);
        userService = new UserService(daoHelperFactory);
    }

    @Before
    public void setUp() {
        usersList = Arrays.asList(firstUser, secondUser);
    }


    @Test
    public void testUserServiceShouldReturnUserWhenLoginAndPasswordSubmitted() throws DaoException, ServiceException {
        //given
        String login = "login";
        String password = "password";
        when(userDao.findUserByLoginAndPassword(login, password))
                .thenReturn(
                        Optional.of(firstUser));
        //when
        Optional<User> optionalUser = userService.login(login, password);
        User actual = optionalUser.get();
        //then
        Assert.assertEquals(firstUser, actual);
    }

    @Test
    public void testUserServiceShouldReturnUserInfoWhenLoginSubmitted() throws DaoException, ServiceException {
        //given
        String login = "login";
        when(userDao.findUserByLogin(login))
                .thenReturn(
                        Optional.of(firstUser));
        //when
        Optional<User> optionalUser = userService.getInfo(login);
        User actualUser = optionalUser.get();
        //then
        Assert.assertEquals(firstUser, actualUser);
    }

    @Test
    public void testUserServiceShouldReturnListOfUsersWhenGetAllUsersRequested() throws DaoException, ServiceException {
        //given
        when(userDao.getAll())
                .thenReturn(usersList);
        //when
        List<User> actual = userService.getAllUsers();
        //then
        Assert.assertEquals(usersList, actual);
    }

    @Test
    public void testUserServiceShouldReturnListOfUsersWhenGetRatingRequested() throws DaoException, ServiceException {
        //given
        when(userDao.getByPoints())
                .thenReturn(usersList);
        //when
        List<User> actual = userService.getRating();
        //then
        Assert.assertEquals(usersList,actual);
    }
}