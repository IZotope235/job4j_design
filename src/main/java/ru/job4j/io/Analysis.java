package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean serverOn = true;
        List<String[]> listIn;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {
            listIn = read.lines()
                    .map(s -> s.split(" ", 2))
                    .collect(Collectors.toList());
            for (String[] arr : listIn) {
                if(serverOn && (arr[0].equals("400") || arr[0].equals("500"))) {
                    stringBuilder.append(arr[1]);
                    stringBuilder.append(";");
                    serverOn = false;
                }
                if(!serverOn && (arr[0].equals("200") || arr[0].equals("300"))) {
                    stringBuilder.append(arr[1]);
                    stringBuilder.append(";");
                    stringBuilder.append(System.lineSeparator());
                    serverOn = true;
                }
            }
            out.printf(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
