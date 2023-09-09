package ru.job4j.collection;

public interface SimpleSet<T> extends Iterable<T> {
    boolean add(T value);
    boolean contains(T value);
}
