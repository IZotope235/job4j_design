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
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> lastElement = head;
            while (lastElement.next != null) {
                lastElement = lastElement.next;
            }
            lastElement.next = new Node<>(value, null);
        }
        size++;
        modCount++;
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
            private Node<E> point = null;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (head == null) {
                    return false;
                }
                if (point == null) {
                    return true;
                }
                return point.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (point == null) {
                    point = head;
                } else {
                    point = point.next;
                }
                return point.item;
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
