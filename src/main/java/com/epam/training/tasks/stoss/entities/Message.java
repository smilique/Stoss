package com.epam.training.tasks.stoss.entities;

import java.sql.Date;
import java.sql.Time;

public class Message implements Entity {

    public static final String TABLE = "chat";
    public static final String ID = "id";
    public static final String MESSAGE = "message";
    public static final String TIME =  "time";
    public static final String USER_NAME = "name";

    private Long id;
    private String text;
    private String username;
    private Time time;
    private Date date;

    public Message(Long id, String text, String username, Time time, Date date) {
        this.id = id;
        this.text = text;
        this.username = username;
        this.time = time;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public Long getId() {
        return id;
    }
}
