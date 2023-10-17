package ru.job4j.search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Search {

    private final String startDirectory;
    private final String pattern;
    private final String type;
    private final String targetFile;

    private Search(ArgsName argsName) {
        validateSearchArguments(argsName);
        this.startDirectory = argsName.get("d");
        this.pattern = argsName.get("n");
        this.type = argsName.get("t");
        this.targetFile = argsName.get("o");
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(input());
        Search search = new Search(argsName);
        SearchVisitor searchVisitor = search.searchVisitorGenerator();
        Files.walkFileTree(Paths.get(search.startDirectory), searchVisitor);
        search.print(searchVisitor.getSearchResultsList());
    }

    private SearchVisitor searchVisitorGenerator() {
        SearchVisitor searchVisitor;
        switch (type) {
            case ("name") ->
                searchVisitor = new SearchVisitorString(pattern);
            case ("regex") ->
                searchVisitor = new SearchVisitorRegex(pattern);
            case ("mask") -> {
                String searchingPattern = pattern.replace(".", "\\.")
                        .replace("*", ".*").replace("?", ".");
                searchVisitor = new SearchVisitorRegex(searchingPattern);
            }
            default -> throw new IllegalArgumentException("Illegal type argument");
        }
        return searchVisitor;
    }

    private static String[] input() {
        System.out.println("Enter search arguments:");
        Scanner scanner = new Scanner(System.in);
        String scannerString = scanner.nextLine();
        return scannerString.split("\\s+");
    }

    private void print(List<String> searchResultsList) {
        searchResultsList.forEach(System.out::println);
        Path outFile = Paths.get("./data/" + targetFile);
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(outFile.toString(), StandardCharsets.UTF_8, false))) {
            for (String string : searchResultsList) {
                pw.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateSearchArguments(ArgsName argsName) {
        String startDirectory = argsName.get("d");
        String searchType = argsName.get("t");
        String targetFile = argsName.get("o");

        if (!Paths.get(startDirectory).toFile().exists()) {
            throw new IllegalArgumentException("The start path is not exist");
        }
        if (Arrays.stream(SearchType.values())
                .map(SearchType::get)
                .noneMatch(s -> s.equals(searchType))) {
            throw new IllegalArgumentException("Illegal search type argument");
        }
        if (!targetFile.endsWith(".txt")) {
            throw new IllegalArgumentException("Illegal target file extension");
        }
        if (!targetFile.matches("^[A-Za-z0-9.]{1,255}$")) {
            throw new IllegalArgumentException("Illegal name of target file");
        }
    }
}
