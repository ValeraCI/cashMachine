package com.company.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<Card, Double> informationAboutCards = new HashMap<>();
    private static Bank bank;

    private Bank(){}

    public static Bank getEntity(){
        if(bank == null) bank = new Bank();
        return bank;
    }

    public boolean cardIsRegistered(String number){
        List<Card> cardList = new ArrayList<>(informationAboutCards.keySet());
        for (Card card: cardList) {
            if(card.getNumber().equals(number)) return true;
        }
        return false;
    }

    public Card getCard(String number){
        List<Card> cardList = new ArrayList<>(informationAboutCards.keySet());
        for (Card card: cardList) {
            if(card.getNumber().equals(number)) return card;
        }
        return null;
    }

    public double getCardBalance(Card card){
        return informationAboutCards.get(card);
    }

    public boolean addNewCard(Card card){
        if(informationAboutCards.containsKey(card)) return false;
        informationAboutCards.put(card, 0d);
        return true;
    }

    public boolean writeOffFunds(Card card, double count){
        double cardBalance = informationAboutCards.get(card);
        if(cardBalance < count) return false;
        informationAboutCards.put(card, Math.round((cardBalance-count)*100)/100d);
        return true;
    }

    public void accrueFunds(Card card, double count){
        double cardBalance = informationAboutCards.get(card);
        informationAboutCards.put(card, Math.round((cardBalance+count)*100)/100d);
    }

    public Map<Card, Double> getInformationAboutCards() {
        return informationAboutCards;
    }

    public void setInformationAboutCards(Map<Card, Double> informationAboutCards) {
        this.informationAboutCards = informationAboutCards;
    }
}
