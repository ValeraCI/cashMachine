package com.company.services;

import com.company.repositories.FileRepository;
import com.company.entity.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileService {

    public static double getCashMachineAmountOfFunds(){
        return FileRepository.getCashMachineAmountOfFunds();
    }

    public static Map<Card, Double> getCards(){
        return FileRepository.getCards();
    }

    public static void writeToFile(double cashMachineAmountOfFunds, Map<Card, Double> cards){
        List<String> text = new ArrayList<>();
        text.add(String.valueOf(cashMachineAmountOfFunds));
        for (Map.Entry<Card, Double> card : cards.entrySet()) {
                text.add(card.getKey() + " " + card.getValue());
        }
        FileRepository.writeToFile(text);
    }
}
