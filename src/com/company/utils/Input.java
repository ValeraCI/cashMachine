package com.company.utils;

import java.math.BigDecimal;
import java.util.Scanner;

public class Input {
    static Scanner scanner = new Scanner(System.in);

    public static String inNotNullStr(){
        while (true){
            String str = scanner.nextLine().trim();
            if(!str.equals("")) return str;
            else System.out.println("Строка не должна быть пустой");
        }
    }

    public static String inPassword(){
        while (true){
            System.out.print("Ввод пароля(содержат только английские буквы, цифры, знак '_'." +
                            " Длина пароля от 4 символов): ");
            String str = scanner.nextLine().trim();
            if(str.matches("\\w{4,}")) return str;
            else System.out.println("Несоответствует требованиям");
        }
    }

    public static BigDecimal inAmountOfFunds(){
        while (true) {
            String inputText = scanner.nextLine();
            try {
                BigDecimal d = new BigDecimal(inputText);
                if(d.compareTo(BigDecimal.valueOf(0)) > 0 && d.scale() <= 2) return d;
            }catch (Exception e){
                System.out.println("Введено не число");
            }
            System.out.println("Введите положительное число с двумя знаками после точки");
        }
    }

    public static String inputCardNumber(){
        while (true) {
            System.out.print("Введите номер карты(в формате XXXX-XXXX-XXXX-XXXX, где X - число): ");
            String cardNumber = scanner.nextLine().trim();
            if (!CardNumberValidator.check(cardNumber)) {
                System.out.println("Карта неверного формата");
                continue;
            }
            if (!CardNumberValidator.isValidLuhn(cardNumber)) {
                System.out.println("Такая карта существовать не может");
                continue;
            }
            return cardNumber;
        }
    }

    public static CardType inputCardType(){
        while (true){
            System.out.print("""
                    Выберите тип карты:
                    1. Золотая(нулевой процент при пополнении и снятии средств)
                    2. Серебренная(при пополнении и снятии накладывается три процента штрафа)
                    3. Бронзовая(при пополнении и снятии накладывается пять процентов штрафа)
                    
                    Ваш выбор:\s""");

            switch (scanner.nextLine()){
                case "1" -> { return CardType.GOLDEN; }
                case "2" -> { return CardType.SILVERED; }
                case "3" -> {return CardType.BRONZE; }
                default -> System.out.println("Неверный ввод");
            }
        }
    }
}
