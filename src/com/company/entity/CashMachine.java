package com.company.entity;

import com.company.services.FileService;

import java.math.BigDecimal;

public class CashMachine {
    private BigDecimal amountOfFunds;
    private final Bank bank = Bank.getInstance();
    private Card card;

    public CashMachine() {
        this.amountOfFunds = FileService.getCashMachineAmountOfFunds();
        bank.setCardsInformation(FileService.getCards());
    }

    public BigDecimal getAmountOfFunds() {
        return amountOfFunds;
    }

    public String getCardNumber(){
        if(card != null) return card.getNumber();
        throw new NullPointerException("Отсутствует карта");
    }

    public Boolean containsCard(){
        return (card != null);
    }

    public Boolean insertCard(String cardNumber, String password) {
        Card card = bank.getCard(cardNumber);
        if(!card.checkPassword(password)) return false;
        this.card = card;
        return true;
    }

    public Boolean registerAndInsert(String cardNumber, String password){
        Card card = new Card(cardNumber, password);
        this.card = card;
        return bank.addCard(card);
    }

    public void extractCard(){
        card = null;
    }

    public Boolean withdrawFunds(BigDecimal count){
        if(!bank.writeOffFunds(card, count)) return false;
        amountOfFunds = amountOfFunds.subtract(count);
        return true;
    }

    public Boolean depositFunds(BigDecimal count){
        if(count.compareTo(BigDecimal.valueOf(1_000_000)) > 0) return false;
        bank.accrueFunds(card, count);
        amountOfFunds = amountOfFunds.add(count);
        return true;
    }

    public BigDecimal getCardBalance(){
        return bank.getCardBalance(card);
    }

    public void blockCard(String cardNumber) {
        bank.getCard(cardNumber).block();
    }

    public void save(){
        FileService.writeToFile(amountOfFunds, bank.getCardsInformation());
    }
}
