package com.epam.training.tasks.stoss.entities;


import java.math.BigDecimal;
import java.util.Objects;

public class User implements Entity{

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BALANCE = "balance";
    public static final String POINTS = "points";
    public static final String LOCALE = "locale";
    public static final String PICTURE = "userpic";
    public static final String PASSWORD = "password";
    public static final String LOGIN = "login";
    public static final String ROLE = "r.name";

    private String name;
    private final Long id;
    private BigDecimal balance;
    private Long points;
//    private String login;
//    private String password;
    private String role;
    private String userpic;
    private String locale;

    public BigDecimal getBalance() {
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

    public String getUserpic() {
        return userpic;
    }

    public String getLocale() {
        return locale;
    }

    public User(Long id, String name, BigDecimal balance, Long points, String role, String locale, String userpic) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.points = points;
        this.role = role;
        this.userpic = userpic;
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", balance=" + balance +
                ", points=" + points +
                ", role='" + role + '\'' +
                ", userpic='" + userpic + '\'' +
                ", locale='" + locale + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

