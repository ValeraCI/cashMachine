package com.company.utils;

import com.company.entity.Bank;
import com.company.entity.Card;
import com.company.entity.CashMachine;
import com.company.service.FileService;

import java.math.BigDecimal;

public class CashMachineMenu {

    private static final CashMachine cashMachine = new CashMachine();
    private static final Bank bank = new Bank();

    private static void saveData(){
        FileService.writeToFile(cashMachine.getAmountOfFunds(), bank.getRegisteredCards());
    }

    private static Boolean cardConfirmation(Card card){
        if (card.isBlocked()) {
            System.out.println("Карта заблокирована");
            return false;
        }

        for (int i = 1; i < 4; i++) {
            System.out.print("Попытка " + i + "/3: ");
            String password = Input.inPassword();
            if (card.checkPassword(password)) {
                System.out.println("Пароль верен\n");
                return true;
            }
            System.out.println("Пароль неверен");
        }

        card.block();
        System.out.println("Карта заблокирована. Повторите попытку через сутки");
        saveData();

        return false;
    }

    private static Card cardRegistration(String cardNumber) {
        System.out.println("Карта впервые активирована. Придумайте пароль");
        String password = Input.inPassword();
        CardType cardType = Input.inputCardType();

        Card card = CardFactory.create(cardNumber, password, cardType);
        bank.addCard(card);
        return card;
    }

    private static Card insertCardMenu(){
        Card card;
        while (true){
            String cardNumber = Input.inputCardNumber();
            if(bank.cardIsRegistered(cardNumber)) {
                    card = bank.getCard(cardNumber);
                    if(cardConfirmation(card)){
                        cashMachine.setContainsCard(true);
                        return card;
                    }
            }
            else {
                cashMachine.setContainsCard(true);
                card = cardRegistration(cardNumber);
                saveData();
                return card;
            }
        }
    }

    private static void topUpCard(Card card){
        System.out.print("Сумма пополнения(не более 1000000): ");
        BigDecimal count = Input.inAmountOfFunds();
        if(count.compareTo(BigDecimal.valueOf(1_000_000)) <= 0) {
            cashMachine.depositFunds(count);
            card.depositFunds(count);
            System.out.println("Операция произведена успешно");
        }
        else System.out.println("Превышение придела пополнения, внесите сумму не более 1000000");
    }

    private static void removeFromCard(Card card) {
        System.out.print("Сумма снятия: ");
        BigDecimal count = Input.inAmountOfFunds();
        if(cashMachine.getAmountOfFunds().compareTo(card.fundsForIssuance(count)) < 0) {
            System.out.println("В банкомате недостаточно средств");
        } else if (!card.possibleWriteOffFunds(count)) {
            System.out.println("На счету недостаточно средств");
        } else {
            BigDecimal cash = card.writeOffFunds(count);
            cashMachine.withdrawFunds(cash);
            System.out.println("Операция выполнена успешна. Выдано: " + cash);
        }
    }

    private static void cardOperationsMenu(Card card){
        boolean work = true;
        while (work){
            System.out.println("Работа с картой №" + card.getNumber() + "(" + card.getType() + ")\n" );

            System.out.print("""
                    Выберите действие:
                    1.Просмотреть баланс карты
                    2.Пополнить баланс
                    3.Снять деньги с баланса

                    0.Извлечь карту
                    Действие номер:\s""");

            switch (Input.inNotNullStr()){
                case "1" -> System.out.println("Баланс: " + card.getFunds());
                case "2" -> topUpCard(card);
                case "3" ->removeFromCard(card);

                case "0" -> {
                    cashMachine.setContainsCard(false);
                    work = false;
                }
                default -> System.out.println("Повторите выбор действия");
            }
            System.out.println();
            saveData();
        }
    }

    public static void start(){
        Card card = null;
        bank.setRegisteredCards(FileService.getCards());
        while (true){
            if(!cashMachine.containsCard()){
                card = insertCardMenu();
            }
            else{
                cardOperationsMenu(card);
            }
        }
    }
}
