package com.company.entity;

import java.math.BigDecimal;
import java.util.*;

public class Bank {
    private Map<Card, BigDecimal> cardsInformation;
    private static Bank bank;

    private Bank(){
        cardsInformation = new HashMap<>();
    }

    public static Bank getInstance(){
        if(bank == null) bank = new Bank();
        return bank;
    }

    public Boolean cardIsRegistered(String number){
        Optional<Card> card = new ArrayList<>(cardsInformation.keySet())
                .stream()
                .filter(c -> c.getNumber().equals(number))
                .findFirst();

        return card.isPresent();
    }

    public Card getCard(String number) {
        Optional<Card> card = new ArrayList<>(cardsInformation.keySet())
                .stream()
                .filter(c -> c.getNumber().equals(number))
                .findFirst();

        if(card.isPresent()) return card.get();
        else throw new NullPointerException("Карта не найдена");
    }

    public BigDecimal getCardBalance(Card card){
        return cardsInformation.get(card);
    }

    public Boolean addCard(Card card){
        if(cardsInformation.containsKey(card)) return false;
        cardsInformation.put(card, new BigDecimal(0));
        return true;
    }

    public Boolean writeOffFunds(Card card, BigDecimal count){
        BigDecimal cardBalance = cardsInformation.get(card);

        if(cardBalance.compareTo(count) < 0) return false;
        cardsInformation.put(card, cardBalance.subtract(count));
        return true;
    }

    public void accrueFunds(Card card, BigDecimal count){
        BigDecimal cardBalance = cardsInformation.get(card);
        cardsInformation.put(card, cardBalance.add(count));
    }

    public Map<Card, BigDecimal> getCardsInformation() {
        return cardsInformation;
    }

    public void setCardsInformation(Map<Card, BigDecimal> cardsInformation) {
        this.cardsInformation = cardsInformation;
    }
}