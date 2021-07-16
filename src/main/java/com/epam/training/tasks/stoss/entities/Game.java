package com.epam.training.tasks.stoss.entities;

import java.util.Objects;

public class Game {

    private final Long userId;
    private final Deck punterDeck;
    private Deck bankerDeck;
    private Long bet;

    public Long getBet() {
        return bet;
    }

    public void setBet(Long bet) {
        this.bet = bet;
    }

    public Game(Long userId) {
        punterDeck = new Deck();
        bankerDeck = new Deck();
        this.userId = userId;
    }

    public Deck getPunterDeck() {
        return punterDeck;
    }

    public Deck getBankerDeck() {
        return bankerDeck;
    }

    public void setBankerDeck(Deck bankerDeck) {
        this.bankerDeck = bankerDeck;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Game{" +
                "userId=" + userId +
                ", punterDeck=" + punterDeck +
                ", bankerDeck=" + bankerDeck +
                ", bet=" + bet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(userId, game.userId) && Objects.equals(punterDeck, game.punterDeck) && Objects.equals(bankerDeck, game.bankerDeck) && Objects.equals(bet, game.bet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, punterDeck, bankerDeck, bet);
    }
}
