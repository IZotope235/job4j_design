package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, ArrayList<String>> fileMap = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        var dubList = new ArrayList<>(List.of(file.toFile().getAbsolutePath()));
        fileMap.merge(fileProperty, dubList, (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        });
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, ArrayList<String>> getFileMap() {
        return fileMap;
    }
}
