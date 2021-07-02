package com.epam.training.tasks.stoss.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class NewsItem implements Entity {

    public static final String TABLE = "news";
    public static final String ID = "id";
    public static final String CAPTION = "caption";
    public static final String TEXT = "text";
    public static final String TIME =  "time";

    private Long id;
    private String caption;
    private String text;
    private Date date;
    private Time time;

    public NewsItem(Long id, String caption, String text, Date date, Time time) {
        this.id = id;
        this.caption = caption;
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public NewsItem(String caption, String text) {
        this.caption = caption;
        this.text = text;
    }

    public String getCaption() {
        return caption;
    }

    public String getText() {
        return text;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsItem item = (NewsItem) o;
        return Objects.equals(id, item.id) && Objects.equals(caption, item.caption) && Objects.equals(text, item.text) && Objects.equals(date, item.date) && Objects.equals(time, item.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caption, text, date, time);
    }
}
