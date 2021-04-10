package com.epam.training.tasks.stoss.entities;

public class Card {
    private String name;
    private String suit;
    private String code;

    private Card() {
        this.code = ""; //not implemented, needs to be random card from enum
    }

    public Card getCard(Deck deck) {
        Card card = new Card();
        //It might be dangerous to use while in such case
        while (deck.getCards().contains(card)) {
            card = new Card();
        }
        return card;
    }
}
