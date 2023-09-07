package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int size = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        for (int i = 0; i < size; i++) {
            out.push(in.pop());
        }
        T output = out.pop();
        size--;
        for (int i = 0; i < size; i++) {
            in.push(out.pop());
        }
        return output;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
