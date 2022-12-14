package com.company.service;

import com.company.repository.FileRepository;
import com.company.entity.Card;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public static BigDecimal getCashMachineAmountOfFunds(){
        return FileRepository.getCashMachineAmountOfFunds();
    }

    public static List<Card> getCards(){
        return FileRepository.getCards();
    }

    public static void writeToFile(BigDecimal cashMachineAmountOfFunds, List<Card> cards){
        List<String> text = new ArrayList<>();
        text.add(String.valueOf(cashMachineAmountOfFunds));
        for (Card card : cards) {
                text.add(card.toString());
        }
        FileRepository.writeToFile(text);
    }
}
