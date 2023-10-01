package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean serverOn = true;
        String[] bufferArray;
        String bufferString;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {
            while (!((bufferString = in.readLine()) == null)) {
                bufferArray = bufferString.split(" ", 2);
                if (serverOn && (bufferArray[0].equals("400") || bufferArray[0].equals("500"))) {
                    out.printf("%s;", bufferArray[1]);
                    serverOn = false;
                }
                if (!serverOn && (bufferArray[0].equals("200") || bufferArray[0].equals("300"))) {
                    out.printf("%s;%n", bufferArray[1]);
                    serverOn = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
