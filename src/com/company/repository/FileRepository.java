package com.company.repository;

import com.company.entity.Card;
import com.company.utils.CardFactory;
import com.company.utils.CardType;
import com.company.utils.FileOperations;
import com.company.utils.Rounding;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
    private static File file;

    private static final List<String> text;

    static {
        String rootFolder = System.getProperty("user.dir");

        new File(rootFolder + "/cashMachineData").mkdirs();
        try {
            (file = new File(rootFolder + "/cashMachineData/data.txt")).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = FileOperations.readFile(file.getPath());
    }


    public static BigDecimal getCashMachineAmountOfFunds(){
        final String defaultValue = "5000";
        if(text.size() == 0) return new BigDecimal(defaultValue);
        String firstLine = text.get(0);
        if(firstLine.matches("^\\d+(.\\d+)?$"))
            return new BigDecimal(text.get(0));

        return new BigDecimal(defaultValue);
    }

    public static List<Card> getCards(){
        List<Card> cards = new ArrayList<>();

        for(int i = 1; i < text.size(); i++) {
            String line = text.get(i).trim();
            if (!line.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4} \\w+ -?\\d+ \\d+(.\\d+)? (BRONZE|GOLDEN|SILVERED)"))
                continue;

            try {
                String[] elementOfLine = line.split(" ");
                Card card = CardFactory.create(elementOfLine[0], elementOfLine[1], CardType.valueOf(elementOfLine[4]));
                card.setBlockingTime(Long.parseLong(elementOfLine[2]));
                card.setFunds(Rounding.bigDecimalToHundredths(new BigDecimal(elementOfLine[3])));
                cards.add(card);
            }catch (Exception e){}
        }
        return cards;
    }

    public static void writeToFile(List<String> text){
        FileOperations.writeToFile(file.getPath(), text);
    }
}
