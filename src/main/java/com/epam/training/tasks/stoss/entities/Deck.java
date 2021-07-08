package com.epam.training.tasks.stoss.entities;

import java.util.Collections;
import java.util.Objects;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards = new Stack<>();

    public Deck() {
        getShuffledDeck();
    }

    private void getShuffledDeck() {
        for (Cards cardElement: Cards.values()) {
            cards.add(new Card(cardElement));
        }
        shuffle();
    }

    public Card getByCode(String code) {
        Card result = null;
        for (Card card: cards) {
            String currentCode = card.getCode();
            if (currentCode.equals(code)) {
                result =  card;
            }
        }
        return result;
    }

    public Card getCard() {
        return cards.pop();
    }


    public Stack<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
