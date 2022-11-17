package com.company.utils;

import com.company.entity.Cards.BronzeCard;
import com.company.entity.Cards.Card;
import com.company.entity.Cards.GoldenCard;
import com.company.entity.Cards.SilveredCard;

public class CardFactory {
    private CardFactory(){}

    public static Card create(String number, String password, CardType cardType){
        Card card;
        switch (cardType){
            case GOLDEN -> card = new GoldenCard(number, password);
            case SILVERED -> card = new SilveredCard(number, password);
            case BRONZE -> card = new BronzeCard(number, password);
            default -> throw new RuntimeException("Неверный тип карты");
        }
        return card;
    }
}
