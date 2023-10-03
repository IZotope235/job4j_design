package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("File not exist %s", file.getAbsolutePath())
            );
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not file %s", file.getAbsoluteFile())
            );
        }
        System.out.printf("file name : %s, size : %d%n", file.getName(), file.length());
    }
}
