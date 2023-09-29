package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read, number;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] stringNumbers = text.toString().split(System.lineSeparator());
            for (String stringNumber : stringNumbers) {
                number = Integer.parseInt(stringNumber);
                System.out.println("Number " + number + " : " + ((number % 2 == 0) ? "even" : "odd"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
