package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import com.epam.training.tasks.stoss.entities.NewsItem;
import com.epam.training.tasks.stoss.mappers.NewsRowMapper;
import com.epam.training.tasks.stoss.services.NewsService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class NewsDao extends AbstractDao<NewsItem> {

    private static final String READ_QUERY = "select * from " + NewsItem.TABLE +" where id = ?";

    protected NewsDao(ProxyConnection connection) {
        super(connection, new NewsRowMapper());
    }
//TODO - it's not from here
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("text/html;charset=UTF-8");
//
//        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
//        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
//
//        NewsService newsService = new NewsService();
//        List<NewsItem> news = newsService.findNews(currentPage, recordsPerPage);
//
//        request.setAttribute("news", news);
//        int rows = newsService.getLength();
//        int pages = rows / recordsPerPage;
//        if (pages % recordsPerPage > 0) {
//            pages++;
//        }
//
//        request.setAttribute("pages", pages);
//        request.setAttribute("currentPage", currentPage);
//        request.setAttribute("recordsPerPage", recordsPerPage);
//
//
//    }

    @Override
    protected String getTableName() {
        return NewsItem.TABLE;
    }

    @Override
    public Optional<NewsItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(NewsItem item) throws DaoException {

    }

    @Override
    public void removeById(Long id) {

    }
}
