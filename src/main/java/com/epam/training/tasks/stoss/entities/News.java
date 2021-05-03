package com.epam.training.tasks.stoss.entities;

import java.util.ArrayList;
import java.util.List;

public class News {

    private List<NewsItem> news = new ArrayList<>();
    private int pagesCount;

    public void addItem (NewsItem item) {
        news.add(item);
    }

    public void setAll (List<NewsItem> items) {
        this.news = items;
    }

    public List<NewsItem> getAll () {
        return news;
    }

    public void setPagesCount (int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getPagesCount() {
        return pagesCount;
    }
}
