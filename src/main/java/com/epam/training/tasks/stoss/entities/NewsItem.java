package com.epam.training.tasks.stoss.entities;

import java.sql.Date;

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

    public NewsItem(Long id, String caption, String text, Date date) {
        this.id = id;
        this.caption = caption;
        this.text = text;
        this.date = date;
    }

    public String getCaption() {
        return caption;
    }

    public String getText() {
        return text;
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




}
