package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.News;
import com.epam.training.tasks.stoss.entities.NewsItem;
import com.epam.training.tasks.stoss.services.NewsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ShowNewsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowNewsCommand.class);

    private final NewsService newsService;

    private static final String PAGE = "WEB-INF/view/news.jsp";

    public ShowNewsCommand(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int numberOfItems = Integer.parseInt(request.getParameter("items"));

        LOGGER.debug("currentPage: " + currentPage);

        try {
            News news = newsService.findNews(currentPage,numberOfItems);
            List<NewsItem> items = news.getAll();
            request.setAttribute("news", items);
            request.setAttribute("currentPage", currentPage);

            int pagesCount = news.getPagesCount();
            LOGGER.debug("pages count: " + pagesCount);
            request.setAttribute("pages", pagesCount);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return CommandResult.forward(PAGE);
    }

}
