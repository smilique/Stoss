package com.epam.training.tasks.stoss.entities;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards = new Stack<>();

    public Deck() {
        getDeck();
    }

    private void getDeck() {
        //sets Deck of 52 cards
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


    public Stack<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void split(int cardNumber) {
        //splits the deck into two subdecks and reorder

    }
}
