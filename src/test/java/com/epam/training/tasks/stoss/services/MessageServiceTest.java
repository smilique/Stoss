package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.dao.*;
import com.epam.training.tasks.stoss.entities.Message;
import com.epam.training.tasks.stoss.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

    private static MessageService messageService;
    private static MessageDao messageDao;

    private final Message firstMessage = new Message("MessageText 1", 1L);
    private final Message secondMessage = new Message("MessageText 2", 2L);
    private List<Message> messagesList = new ArrayList<>();

    @BeforeClass
    public static void beforeClass() throws Exception {
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        when(daoHelperFactory.create())
                .thenReturn(daoHelper);
        messageDao = Mockito.mock(MessageDao.class);
        when(daoHelper.createMessageDao())
                .thenReturn(messageDao);
        messageService = new MessageService(daoHelperFactory);
    }

    @Before
    public void setUp() {
        messagesList = Arrays.asList(firstMessage, secondMessage);
    }

    @Test
    public void testMessageServiceShouldReturnAllMessages() throws DaoException, ServiceException {
        //given
        when(messageDao.getAll())
                .thenReturn(messagesList);
        //when
        List<Message> actual = messageService.getMessages();
        //then
        Assert.assertEquals(messagesList, actual);

    }
}