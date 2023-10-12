package ru.job4j.search;

public enum SearchType {
    MASK("mask"), REGEX("regex"), NAME("name");

    private final String type;

    SearchType(String type) {
    this.type = type;
    }

    public String get() {
        return type;
    }
}
