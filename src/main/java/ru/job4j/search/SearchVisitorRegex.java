package ru.job4j.search;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchVisitorRegex implements SearchVisitor {

    private final List<String> searchResultsList = new ArrayList<>();
    private final String searchingPattern;

    public SearchVisitorRegex(String searchingPattern) {
        this.searchingPattern = searchingPattern;
    }

    @Override
    public List<String> getSearchResultsList() {
        return searchResultsList;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        if (fileName.matches(searchingPattern)) {
            searchResultsList.add(file.toFile().getAbsolutePath());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
