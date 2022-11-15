package com.company.services;

import com.company.repositories.FileRepository;
import com.company.entity.Card;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileService {

    public static BigDecimal getCashMachineAmountOfFunds(){
        return FileRepository.getCashMachineAmountOfFunds();
    }

    public static Map<Card, BigDecimal> getCards(){
        return FileRepository.getCards();
    }

    public static void writeToFile(BigDecimal cashMachineAmountOfFunds, Map<Card, BigDecimal> cards){
        List<String> text = new ArrayList<>();
        text.add(String.valueOf(cashMachineAmountOfFunds));
        for (Map.Entry<Card, BigDecimal> card : cards.entrySet()) {
                text.add(card.getKey() + " " + card.getValue());
        }
        FileRepository.writeToFile(text);
    }
}
