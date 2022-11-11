package com.company.entity;

import com.company.services.FileService;

public class CashMachine {
    private double amountOfFunds;
    private final Bank bank = Bank.getEntity();;
    private Card card;

    public CashMachine() {
        this.amountOfFunds = FileService.getCashMachineAmountOfFunds();
        bank.setInformationAboutCards(FileService.getCards());
    }

    public double getAmountOfFunds() {
        return amountOfFunds;
    }

    public String getCardNumber(){
        if(card != null) return  card.getNumber();
        else return null;
    }

    public boolean containsCard(){
        return (card != null);
    }

    public boolean insertCard(String cardNumber, String password){
        //возможно стоит убрать метод проверки наличия карты и проверять по null значению(Если null - то карты нет)
        Card card = bank.getCard(cardNumber);
        if(!card.checkPassword(password)) return false;
        this.card = card;
        return true;
    }

    public void registerAndInsert(String cardNumber, String password){
        Card card = new Card(cardNumber, password);
        bank.addNewCard(card);
        this.card = card;
    }

    public void extractCard(){
        card = null;
    }

    public boolean withdrawFunds(double count){
        if(!bank.writeOffFunds(card, count)) return false;
        amountOfFunds -= count;
        return true;
    }

    public boolean depositFunds(double count){
        if(count > 1_000_000) return false;
        bank.accrueFunds(card, count);
        amountOfFunds += count;
        return true;
    }

    public double getCardBalance(){
        return bank.getCardBalance(card);
    }

    public void blockCard(String cardNumber){
        bank.getCard(cardNumber).block();
    }

    public void save(){
        FileService.writeToFile(amountOfFunds, bank.getInformationAboutCards());
    }
}
