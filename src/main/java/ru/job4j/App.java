package ru.job4j;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Path;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Console console = System.console();
        System.out.println(System.in);
    }
}
