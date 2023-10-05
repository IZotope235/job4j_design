package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> listLog = new ArrayList<>();
        List<String> listAnswers = this.readPhrases();
        int listAnswersSize = listAnswers.size();
        String userInput = CONTINUE;
        String botAnswer;
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals(OUT)) {
            if (userInput.equals(CONTINUE)) {
                while (!userInput.equals(STOP)) {
                    botAnswer = listAnswers.get(((int) (Math.random() * listAnswersSize)));
                    listLog.add(botAnswer);
                    System.out.println(botAnswer);
                    userInput = scanner.nextLine();
                    listLog.add(userInput);
                    if (userInput.equals(OUT)) {
                        break;
                    }
                }
            }
            if (userInput.equals(OUT)) {
                break;
            }
            userInput = scanner.nextLine();
            listLog.add(userInput);
        }
        this.saveLog(listLog);
        System.out.println(listLog);
    }

    private List<String> readPhrases() {
        Path botAnswers = Paths.get("data/" + this.botAnswers);
        List<String> listAnswers = new ArrayList<>();
        if (!botAnswers.toFile().exists()) {
            throw new IllegalArgumentException(
                    String.format("File '%s' is not exist", this.botAnswers));
        }
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers.toString()))) {
           listAnswers = in.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listAnswers;
    }

    private void saveLog(List<String> log) {
        Path path = Paths.get("data/" + this.path);
        path.toFile().delete();
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path.toString(), StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("botLog.txt", "botAnswers.txt");
        cc.run();
    }
}
