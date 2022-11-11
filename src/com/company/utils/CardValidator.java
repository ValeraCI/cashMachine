package com.company.utils;

import com.company.entity.Bank;

public class CardValidator {
    static Bank bank = Bank.getEntity();

    public static boolean cardIsRegistered(String cardNumber){
        return bank.cardIsRegistered(cardNumber);
    }

    public static boolean cardIsBlock(String cardNumber){
        return bank.getCard(cardNumber).isBlock();
    }

}
