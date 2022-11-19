package com.company.entity;

import java.util.*;

public class Bank {
    private List<Card> registeredCards;

    public Bank(){
        registeredCards = new ArrayList<>();
    }

    public Boolean cardIsRegistered(String number){
        Optional<Card> card =
                registeredCards
                .stream()
                .filter(c -> c.getNumber().equals(number))
                .findFirst();

        return card.isPresent();
    }

    public Card getCard(String number) {
        Optional<Card> card =
                registeredCards
                .stream()
                .filter(c -> c.getNumber().equals(number))
                .findFirst();

        if(card.isPresent()) return card.get();
        else throw new NullPointerException("Карта не найдена");
    }

    public Boolean addCard(Card card){
        if(registeredCards.contains(card)) return false;
        registeredCards.add(card);
        return true;
    }

    public List<Card> getRegisteredCards() {
        return registeredCards;
    }

    public void setRegisteredCards(List<Card> registeredCards) {
        this.registeredCards = registeredCards;
    }
}