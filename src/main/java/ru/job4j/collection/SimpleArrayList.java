package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int madCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
        size = 0;
        madCount = 0;
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        madCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removeValue = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        madCount++;
        return removeValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = madCount;
            private int point = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != madCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext() && size == 0) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }

    private void grow() {
        if (container.length == 0) {
            container = (T[]) new Object[1];
        }
        container = Arrays.copyOf(container, container.length * 2);
    }
}
