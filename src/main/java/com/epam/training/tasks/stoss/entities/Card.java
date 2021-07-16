package com.epam.training.tasks.stoss.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(name, card.name) && Objects.equals(suit, card.suit) && Objects.equals(code, card.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, suit, code);
    }
}
