package com.company.utils;

import com.company.entity.Bank;

public class CardValidator {
    static Bank bank = Bank.getInstance();

    public static Boolean cardIsRegistered(String cardNumber){
        return bank.cardIsRegistered(cardNumber);
    }

    public static Boolean cardIsBlock(String cardNumber) {
        return bank.getCard(cardNumber).isBlocked();
    }

}
