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
    private Long id;
    private BigDecimal balance;
    private Long points;
    private String login;
    private String role;
    private String userpic;
    private String locale;
    private String password;

    @Override
    public Long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getUserpic() {
        return userpic;
    }
    public String getLocale() {
        return locale;
    }
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

    public void setName(String name) {
        this.name = name;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public void setPoints(Long points) {
        this.points = points;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public User(String login, Long id, String name, BigDecimal balance,
                Long points, String role, String locale, String userpic, String password) {
        this.login = login;
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.points = points;
        this.role = role;
        this.userpic = userpic;
        this.locale = locale;
        this.password = password;
    }

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
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

