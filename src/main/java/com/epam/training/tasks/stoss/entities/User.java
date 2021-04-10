package com.epam.training.tasks.stoss.entities;


public class User implements Entity{

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BALANCE = "balance";
    public static final String PASSWORD = "password";
    public static final String LOGIN = "login";

    private String name;
    private Long id;
    private Long balance;
    private String login;
    private String password;

    public Long getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}

