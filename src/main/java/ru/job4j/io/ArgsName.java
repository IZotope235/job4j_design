package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(
                    String.format("This key: '%s' is missing", key));
        }
        return value;
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(s -> s.substring(1))
                .map(s -> s.split("=", 2))
                .forEach(arr -> values.put(arr[0], arr[1]));
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            validateArgument(arg);
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        names.validateArgumentsForCreateZip();
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    private static void validateArgument(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not start with a '-' character", arg));
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain an equal sign", arg));
        }
        String[] keyValueArr = arg.substring(1).split("=", 2);
        if (keyValueArr[0].isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain a key", arg));
        }
        if (keyValueArr[1].isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain a value", arg));
        }
    }

    private void validateArgumentsForCreateZip() {
        Path path = Paths.get(get("d"));
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("The path is not exist");
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("The path is not directory");
        }
        String fileExtension = get("e");
        if (!fileExtension.startsWith(".")) {
            throw new IllegalArgumentException(("Search pattern does not start with '.' character"));
        }
        if (!fileExtension.matches("^\\.[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Search pattern must be like \".js\"");
        }
        String fileTarget = get("o");
        if (!fileTarget.endsWith(".zip")) {
            throw new IllegalArgumentException(("Target file does not '.zip' file"));
        }
        if (!fileTarget.matches("^[a-zA-Z0-9._-]+\\.[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Target file does not have proper name");
        }
    }
}
