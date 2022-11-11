package com.company.utils;

import com.company.entity.CashMachine;

public class CashMachineMenu {

    private static CashMachine cashMachine = new CashMachine();

    private static boolean cardConfirmation(String cardNumber) {
        if (CardValidator.cardIsBlock(cardNumber)) {
            System.out.println("Карта заблокирована");
            return false;
        }

        for (int i = 1; i < 4; i++) {
            System.out.print("Введите пароль(попытка " + i + "/3): ");
            String password = Input.inNotNullStr();
            if (cashMachine.insertCard(cardNumber, password)) {
                System.out.println("Пароль верен");
                return true;
            }
            System.out.println("Пароль неверен");
        }

        cashMachine.blockCard(cardNumber);
        System.out.println("Карта заблокирована. Повторите попытку через сутки");

        return false;
    }

    private static void cardRegistration(String cardNumber) {
        System.out.print("Карта впервые активирована, придумайте пароль: ");
        String password = Input.inNotNullStr();
        cashMachine.registerAndInsert(cardNumber, password);
    }

    private static void insertCardMenu(){
        boolean work = true;

        while (work){
            String cardNumber = Input.inputCardNumber();
            if(CardValidator.cardIsRegistered(cardNumber))
                work = !cardConfirmation(cardNumber);
            else {
                cardRegistration(cardNumber);
                work = false;
            }
            cashMachine.save();
        }
    }

    public static void topUpCard(){
        System.out.print("Сумма пополнения(не более 1000000): ");
        double count = Input.inDoubleGreaterThanZeroWithTwoNumbersAfterTheDot();
        if(cashMachine.depositFunds(count))
            System.out.println("Операция произведена успешно");
        else System.out.println("Превышение придела пополнения, внесите сумму не более 1000000");
    }

    public static void removeFromCard() {
        System.out.print("Сумма снятия: ");
        double count = Input.inDoubleGreaterThanZeroWithTwoNumbersAfterTheDot();
        if(cashMachine.getAmountOfFunds() < count) System.out.println("В банкомате недостаточно средств");
        else if(!cashMachine.withdrawFunds(count)) System.out.println("На счету недостаточно средств");
        else System.out.println("Операция произведена успешно");
    }

    private static void cardOperationsMenu(){
        boolean work = true;
        while (work){
            System.out.println("Работа с картой №" + cashMachine.getCardNumber() + "\n");

            System.out.println("Выберите действие:\n" +
                    "1.Просмотреть баланс карты\n" +
                    "2.Пополнить баланс\n" +
                    "3.Снять деньги с баланса\n\n" +
                    "0.Извлечь карту");

            System.out.println("Действие номер: ");
            switch (Input.inNotNullStr()){
                case "1" -> System.out.println("Баланс: " + cashMachine.getCardBalance());
                case "2" -> topUpCard();
                case "3" ->removeFromCard();

                case "0" -> {
                    cashMachine.extractCard();
                    work = false;
                }
                default -> System.out.println("Повторите выбор действия");
            }
            System.out.println();
            cashMachine.save();
        }
    }

    public static void start(){
        while (true){
            if(!cashMachine.containsCard()){
                insertCardMenu();
            }
            else{
                cardOperationsMenu();
            }
        }
    }
}
