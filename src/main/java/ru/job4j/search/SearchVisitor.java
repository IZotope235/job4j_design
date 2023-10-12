package ru.job4j.search;

import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.util.List;

public interface SearchVisitor extends FileVisitor<Path> {
    List<String> getSearchResultsList();
}
