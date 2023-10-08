package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private final String source;
    private final String outTarget;
    private final String delimiter;
    private final List<String> filter;
    private final Map<String, Queue<String>> tokens = new HashMap<>();


    private CSVReader(ArgsName argsName) {
        validateCSVArguments(argsName);
        this.source = argsName.get("path");
        this.delimiter = argsName.get("delimiter");
        this.outTarget = argsName.get("out");
        this.filter = Arrays.asList(argsName.get("filter").split(","));
    }

    public static void handle(ArgsName argsName) {
        CSVReader csvReader = new CSVReader(argsName);
        List<String> pattern = patternGenerator(csvReader);
        tokensMapScanner(csvReader, pattern);
        List<String> outputList = new ArrayList<>(csvReader.filter);
        outputList.retainAll(csvReader.tokens.keySet());
        if (outputList.isEmpty()) {
            throw new IllegalArgumentException("Error - empty output.");
        }
        if ("stdout".equals(csvReader.outTarget)) {
            printConsole(csvReader, outputList);
        } else {
            printPath(csvReader, outputList);
        }
    }

    private static List<String> patternGenerator(CSVReader csvReader) {
        List<String> pattern = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(csvReader.source))) {
            String firstLine = in.readLine();
            if (!(firstLine == null)) {
                Arrays.stream(firstLine.split(csvReader.delimiter))
                        .forEach(el -> pattern.add(csvReader.filter.contains(el) ? el : null));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pattern;
    }

    private static void tokensMapScanner(CSVReader csvReader, List<String> pattern) {
        try (var scannerElement = new Scanner(
                new FileReader(csvReader.source))
                .useDelimiter(csvReader.delimiter + "|\r\n|\n")) {
            while (scannerElement.hasNext()) {
                for (String i : pattern) {
                    if (!(i == null)) {
                        csvReader.tokens.computeIfAbsent(i, k -> new LinkedList<>())
                                .add(scannerElement.next());
                    } else {
                        scannerElement.next();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printConsole(CSVReader csvReader, List<String> outputlist) {
        int size = csvReader.tokens.get(outputlist.get(0)).size();
        for (int i = 0; i < size; i++) {
            StringJoiner stringJoiner = new StringJoiner(csvReader.delimiter, "", "\r\n");
            for (String string : outputlist) {
                stringJoiner.add(csvReader.tokens.get(string).poll());
            }
            System.out.print(stringJoiner);
        }

    }

    private static void printPath(CSVReader csvReader, List<String> outputlist) {
        Path outFile = Paths.get(csvReader.outTarget);
        int size = csvReader.tokens.get(outputlist.get(0)).size();
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(outFile.toString(), StandardCharsets.UTF_8, true))) {
            for (int i = 0; i < size; i++) {
                StringJoiner stringJoiner = new StringJoiner(csvReader.delimiter, "", "\r\n");
                for (String string : outputlist) {
                    stringJoiner.add(csvReader.tokens.get(string).poll());
                }
                pw.print(stringJoiner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateCSVArguments(ArgsName argsName) {
        Path source = Paths.get(argsName.get("path"));
        if (!source.toFile().exists()) {
            throw new IllegalArgumentException("The source path is not exist");
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException(("Source file does not '.csv' file"));
        }
        if (!argsName.get("delimiter").matches("[;|,]")) {
            throw new IllegalArgumentException(("Illegal delimiter argument"));
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
