package com.example.chelsi.practicalretake;

import java.util.List;

/**
 * Created by Chelsi on 6/23/2018.
 */

public class CardResponse {
    private boolean success;
    private boolean shuffled;
    private List<CardModel> cards;
    private String deck_id;
    private int remaining;

    public CardResponse(boolean success, boolean shuffled, List<CardModel> cards, String deck_id, int remaining) {
        this.success = success;
        this.shuffled = shuffled;
        this.cards = cards;
        this.deck_id = deck_id;
        this.remaining = remaining;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public List<CardModel> getCards() {
        return cards;
    }

    public void setCards(List<CardModel> cards) {
        this.cards = cards;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
