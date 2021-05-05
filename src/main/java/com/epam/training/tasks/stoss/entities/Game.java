package com.epam.training.tasks.stoss.entities;

public class Game {

    private Deck punterDeck;
    private Deck bankerDeck;
    private Long userId;
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
}
