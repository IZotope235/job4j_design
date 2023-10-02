package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects\\job4j_design\\pom.xml");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("File not exist %s", file.getAbsolutePath())
            );
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException(
                    String.format("Not file %s", file.getAbsoluteFile())
            );
        }
        System.out.printf("file name : %s, size : %d%n", file.getName(), file.length());
    }
}
