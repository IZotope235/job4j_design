package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<String>> fileMap = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        fileMap.computeIfAbsent(fileProperty, k -> new ArrayList<>())
                .add(file.toFile().getAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<String>> getFileMap() {
        return fileMap;
    }
}
