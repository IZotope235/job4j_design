package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = index(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private int index(K key) {
        return indexFor(hash(hashCode(key)));
    }

    private void expand() {
        int newCapacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        Iterator<K> iterator = iterator();
        while (iterator.hasNext()) {
            K key = iterator.next();
            MapEntry<K, V> node = table[index(key)];
            int newIndex = (newCapacity - 1) & hash(hashCode(key));
            if (newTable[newIndex] == null) {
                newTable[newIndex] = node;
            }
        }
        capacity = newCapacity;
        table = newTable;
    }

    @Override
    public V get(K key) {
        int hashCode = hashCode(key);
        int index = index(key);
        if (table[index] == null) {
            return null;
        }
        K keyEl = table[index].key;
        int hashCodeEl = hashCode(keyEl);
        if (hashCodeEl == hashCode && Objects.equals(key, keyEl)) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = index(key);
        if (table[index] != null) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}