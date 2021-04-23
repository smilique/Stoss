package com.epam.training.tasks.stoss.entities;


public class User implements Entity{

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BALANCE = "balance";
    public static final String POINTS = "points";
    public static final String PASSWORD = "password";
    public static final String LOGIN = "login";
    public static final String ROLE = "r.name";

    private String name;
    private Long id;
    private Long balance;
    private Long points;
//    private String login;
//    private String password;
    private String role;

    public Long getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public Long getPoints() {
        return points;
    }

    public String getRole() {
        return role;
    }

    @Override
    public Long getId() {
        return id;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(Long id, String name, Long balance, Long points, String role) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.points = points;
        this.role = role;
    }

}

