package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value, null);
        size++;
        modCount++;
        if (head == null) {
            head = node;
            return;
        }
        Node<E> lastElement = head;
        while (lastElement.next != null) {
            lastElement = lastElement.next;
        }
        lastElement.next = node;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> output = head;
        for (int i = 0; i < index; i++) {
            output = output.next;
        }
        return output.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<E> point = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = point.item;
                point = point.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
