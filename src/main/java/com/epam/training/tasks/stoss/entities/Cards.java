package com.epam.training.tasks.stoss.entities;

public enum Cards {

    ACE_OF_SPADES("\uD83C\uDCA1", "spades", "ace"),
    KING_OF_SPADES("\uD83C\uDCAE", "spades", "king"),
    QUEEN_OF_SPADES("\uD83C\uDCAD", "spades", "queen"),
    JACK_OF_SPADES("\uD83C\uDCAB", "spades", "jack"),
    TEN_OF_SPADES("\uD83C\uDCAA", "spades", "ten"),
    NINE_OF_SPADES("\uD83C\uDCA9", "spades", "nine"),
    EIGHT_OF_SPADES("\uD83C\uDCA8", "spades", "eight"),
    SEVEN_OF_SPADES("\uD83C\uDCA7", "spades", "seven"),
    SIX_OF_SPADES("\uD83C\uDCA6", "spades", "six"),
    FIVE_OF_SPADES("\uD83C\uDCA5", "spades", "five"),
    FOUR_OF_SPADES("\uD83C\uDCA4", "spades", ""),
    THREE_OF_SPADES("\uD83C\uDCA3", "spades", "three"),
    TWO_OF_SPADES("\uD83C\uDCA2", "spades", "two"),

    ACE_OF_CLUBS("\uD83C\uDCD1", "clubs", "ace"),
    KING_OF_CLUBS("\uD83C\uDCDE", "clubs", "king"),
    QUEEN_OF_CLUBS("\uD83C\uDCDD", "clubs", "queen"),
    JACK_OF_CLUBS("\uD83C\uDCDB", "clubs", "jack"),
    TEN_OF_CLUBS("\uD83C\uDCDA", "clubs", "ten"),
    NINE_OF_CLUBS("\uD83C\uDCD9", "clubs", "nine"),
    EIGHT_OF_CLUBS("\uD83C\uDCD8", "clubs", "eight"),
    SEVEN_OF_CLUBS("\uD83C\uDCD7", "clubs", "seven"),
    SIX_OF_CLUBS("\uD83C\uDCD6", "clubs", "six"),
    FIVE_OF_CLUBS("\uD83C\uDCD5", "clubs", "five"),
    FOUR_OF_CLUBS("\uD83C\uDCD4", "clubs", "four"),
    THREE_OF_CLUBS("\uD83C\uDCD3", "clubs", "three"),
    TWO_OF_CLUBS("\uD83C\uDCD2", "clubs", "two"),

    ACE_OF_HEARTS("\uD83C\uDCB1", "hearts", "ace"),
    KING_OF_HEARTS("\uD83C\uDCBE", "hearts", "king"),
    QUEEN_OF_HEARTS("\uD83C\uDCBD", "hearts", "queen"),
    JACK_OF_HEARTS("\uD83C\uDCBB", "hearts", "jack"),
    TEN_OF_HEARTS("\uD83C\uDCBA", "hearts", "ten"),
    NINE_OF_HEARTS("\uD83C\uDCB9", "hearts", "nine"),
    EIGHT_OF_HEARTS("\uD83C\uDCB8", "hearts", "eight"),
    SEVEN_OF_HEARTS("\uD83C\uDCB7", "hearts", "seven"),
    SIX_OF_HEARTS("\uD83C\uDCB6", "hearts", "six"),
    FIVE_OF_HEARTS("\uD83C\uDCB5", "hearts", "five"),
    FOUR_OF_HEARTS("\uD83C\uDCB4", "hearts", "four"),
    THREE_OF_HEARTS("\uD83C\uDCB3", "hearts", "three"),
    TWO_OF_HEARTS("\uD83C\uDCB2", "hearts", "two"),

    ACE_OF_DIAMONDS("\uD83C\uDCC1", "diamonds", "ace"),
    KING_OF_DIAMONDS("\uD83C\uDCCE", "diamonds", "king"),
    QUEEN_OF_DIAMONDS("\uD83C\uDCCD", "diamonds", "queen"),
    JACK_OF_DIAMONDS("\uD83C\uDCCB", "diamonds", "jack"),
    TEN_OF_DIAMONDS("\uD83C\uDCCA", "diamonds", "ten"),
    NINE_OF_DIAMONDS("\uD83C\uDCC9", "diamonds", "nine"),
    EIGHT_OF_DIAMONDS("\uD83C\uDCC8", "diamonds", "eight"),
    SEVEN_OF_DIAMONDS("\uD83C\uDCC7", "diamonds", "seven"),
    SIX_OF_DIAMONDS("\uD83C\uDCC6", "diamonds", "six"),
    FIVE_OF_DIAMONDS("\uD83C\uDCC5", "diamonds", "five"),
    FOUR_OF_DIAMONDS("\uD83C\uDCC4", "diamonds", "four"),
    THREE_OF_DIAMONDS("\uD83C\uDCC3", "diamonds", "three"),
    TWO_OF_DIAMONDS("\uD83C\uDCC2", "diamonds", "two");


    private final String code;
    private final String suit;
    private final String name;

    Cards(String code, String suit, String name) {
        this.code = code;
        this.suit = suit;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getSuit() {
        return suit;
    }

    public String getName() {
        return name;
    }
}
