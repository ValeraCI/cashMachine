package com.company.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {
    public static List<String> readFile(String fileName){
        List<String> text = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))){
            String str;
            while ((str = bf.readLine()) != null){
                text.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void writeToFile(String fileName, List<String> text){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(fileName))) {
            for (String str: text) {
                bf.write(str + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
