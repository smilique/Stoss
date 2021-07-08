package com.epam.training.tasks.stoss.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Message implements Entity {

    public static final String TABLE = "chat";
    public static final String ID = "id";
    public static final String MESSAGE = "message";
    public static final String TIME =  "time";
    public static final String USER_NAME = "name";
    public static final String USER_ID = "user.id";

    private final Long userId;
    private final String text;
    private final Time time;
    private final Date date;

    private Long id;
    private String username;

    public Message(Long id, String text, Long userId, String username, Time time, Date date) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.username = username;
        this.time = time;
        this.date = date;
    }

    public Message(String text, Long userId) {
        date = new Date(System.currentTimeMillis());
        time = new Time(System.currentTimeMillis());
        this.text = text;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
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

    @Override
    public String toString() {
        return "Message{" +
                "userId=" + userId +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(userId, message.userId) && Objects.equals(text, message.text) && Objects.equals(id, message.id) && Objects.equals(username, message.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, text, id, username);
    }
}

