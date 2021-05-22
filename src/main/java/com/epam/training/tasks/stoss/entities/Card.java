package com.epam.training.tasks.stoss.entities;

public class Card {
    private final String name;
    private final String suit;
    private final String code;

    public Card(Cards cardElement) {
        this.code = cardElement.getCode();
        this.suit = cardElement.getSuit();
        this.name = cardElement.getName();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card{" + "name='" + name + " of " + suit + '\'' + ", code=" + code + '}';
    }
}
