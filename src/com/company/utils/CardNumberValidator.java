package com.company.utils;

public class CardNumberValidator {

    private final static String regex = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$";

    public static Boolean check(String cardNumber){
        return cardNumber.matches(regex);
    }

    public static Boolean isValidLuhn(String cardNumber){
        cardNumber = cardNumber.replaceAll("-", "");
        int sum = 0;
        for(int i = 0; i < cardNumber.length(); i++){
            int num = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
            if((i+1) % 2 == 1){
                num *= 2;
                if(num >= 10) num -= 9;
            }
            sum += num;
        }
        return sum % 10 == 0;
    }
}
