package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateArguments(ArgsName argsName) {
        Path path = Paths.get(argsName.get("d"));
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("The path is not exist");
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("The path is not directory");
        }
        String fileExtension = argsName.get("e");
        if (!fileExtension.startsWith(".")) {
            throw new IllegalArgumentException(("Search pattern does not start with '.' character"));
        }
        if (!fileExtension.matches("^\\.[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Search pattern must be like \".js\"");
        }
        String fileTarget = argsName.get("o");
        if (!fileTarget.endsWith(".zip")) {
            throw new IllegalArgumentException(("Target file does not '.zip' file"));
        }
        if (!fileTarget.matches("^[a-zA-Z0-9._-]+\\.[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Target file does not have proper name");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        var argsName = ArgsName.of(args);
        validateArguments(argsName);
        var source = Search.search(Paths.get(argsName.get("d")),
                p -> !p.toFile().getName().endsWith(argsName.get("e")))
                .stream()
                .toList();
        zip.packFiles(source, new File("./" + argsName.get("o")));
    }
}
