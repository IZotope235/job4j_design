package ru.job4j.search;

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
        return names;
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
}
