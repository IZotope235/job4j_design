package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        size++;
        modCount++;
        if (head == null) {
            head = node;
            return;
        }
        Node<T> lastElement = head;
        while (lastElement.next != null) {
            lastElement = lastElement.next;
        }
        lastElement.next = node;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> output = head;
        for (int i = 0; i < index; i++) {
            output = output.next;
        }
        return output.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> deletedItem = head;
        T output = head.item;
        head = head.next;
        deletedItem.next = null;
        deletedItem.item = null;
        size--;
        modCount++;
        return output;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<T> point = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = point.item;
                point = point.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
