package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("c:\\testDub"), duplicatesVisitor);
        getDuplicates(duplicatesVisitor);
    }

    private static void getDuplicates(DuplicatesVisitor duplicatesVisitor) {
        duplicatesVisitor
                .getFileMap()
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .peek(e -> System.out.printf("%s - %d bytes%n", e.getKey().getName(), e.getKey().getSize()))
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }
}
