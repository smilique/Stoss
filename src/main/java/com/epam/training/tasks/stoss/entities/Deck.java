package com.epam.training.tasks.stoss.entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Stack;

public class Deck {
    private Stack<Card> cards = new Stack<>();

    public Deck getFullDeck() {
        //returns Deck of 52 Cards
        throw new NotImplementedException();
    }

    public Deck getShortDeck() {
        //returns Deck of 32 cards
        throw new NotImplementedException();
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        //shuffles the deck
    }

    public void split(int cardNumber) {
        //splits the deck into two subdecks and reorder
    }
}
