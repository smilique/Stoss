package com.epam.training.tasks.stoss.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news1 = (News) o;
        return pagesCount == news1.pagesCount && Objects.equals(news, news1.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(news, pagesCount);
    }

    @Override
    public String toString() {
        return "News{" +
                "news=" + news +
                ", pagesCount=" + pagesCount +
                '}';
    }
}
