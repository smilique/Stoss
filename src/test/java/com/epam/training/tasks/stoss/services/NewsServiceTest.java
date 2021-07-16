package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.dao.DaoException;
import com.epam.training.tasks.stoss.dao.DaoHelper;
import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.dao.NewsDao;
import com.epam.training.tasks.stoss.entities.News;
import com.epam.training.tasks.stoss.entities.NewsItem;
import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.NewsService;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class NewsServiceTest {

    private static NewsDao newsDao;
    private static NewsService newsService;
    private final NewsItem firstNewsItem = new NewsItem(1L, "First Caption", "First text", new Date(System.currentTimeMillis()),
        new Time(System.currentTimeMillis()));
    private final NewsItem secondNewsItem = new NewsItem(1L, "Second Caption", "Second text", new Date(System.currentTimeMillis()),
            new Time(System.currentTimeMillis()));
    private static final int PAGES_COUNT = 1;
    private News news = new News();

    @BeforeClass
    public static void beforeClass() throws Exception {
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        when(daoHelperFactory.create())
                .thenReturn(daoHelper);
        newsDao = Mockito.mock(NewsDao.class);
        when(daoHelper.createNewsDao())
                .thenReturn(newsDao);
        newsService = new NewsService(daoHelperFactory);
    }

    @Before
    public void setUp() {
        news.addItem(firstNewsItem);
        news.addItem(secondNewsItem);
        news.setPagesCount(PAGES_COUNT);
    }

    @Test
    public void testNewsServiceShoudReturnListOfNewsWhenCorrectParametersApplied() throws DaoException, ServiceException {
        //given
        int currentPage = 1;
        int recordsPerPage = 2;
        List<NewsItem> newsItemList = Arrays.asList(secondNewsItem, firstNewsItem);
        when(newsDao.getAll())
                .thenReturn(newsItemList);
        //when
        News actualNews = newsService.findNews(currentPage,recordsPerPage);
        //then
        Assert.assertEquals(news, actualNews);
    }



    @Test (expected = ServiceException.class)
    public void testNewsServiceShouldThrowServiceExceptionWhenIncorrectParametersApplied() throws ServiceException, DaoException {
        //given
        int incorrectValue = 0;
        when(newsDao.getAll()).thenReturn(new ArrayList<>());
        //when
        News actualNews = newsService.findNews(incorrectValue,incorrectValue);
    }
}