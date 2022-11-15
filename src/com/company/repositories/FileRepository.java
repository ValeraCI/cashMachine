package com.company.repositories;

import com.company.entity.Card;
import com.company.utils.FileOperations;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        else return new BigDecimal(defaultValue);
    }

    public static Map<Card, BigDecimal> getCards(){
        Map<Card, BigDecimal> cards = new HashMap<>();

        for(int i = 1; i < text.size(); i++) {
            if (!text.get(i).trim().matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4} \\w+ -?\\d+ \\d+(.\\d+)?"))
                continue;

            String[] elementOfLine = text.get(i).trim().split(" ");
            cards.put(new Card(elementOfLine[0], elementOfLine[1], Long.parseLong(elementOfLine[2])),
                    new BigDecimal(elementOfLine[3]));
        }
        return cards;
    }

    public static void writeToFile(List<String> text){
        FileOperations.writeToFile(file.getPath(), text);
    }
}
