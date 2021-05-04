package com.epam.training.tasks.stoss.entities;

public class Game {

    private Deck punterDeck;
    private Deck bankerDeck;
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }
}
