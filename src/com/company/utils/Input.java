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
            String str = scanner.nextLine().trim();
            if(str.matches("\\w+")) return str;
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
}
