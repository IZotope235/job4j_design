package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

class MatrixItTest  {

    @Test
    void when4El() {
        int[][] input = {
                {1}
        };
        MatrixIt iterator = new MatrixIt(input);
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenFirstEmptyThenNext() {
        int[][] input = {
                {}, {1}
        };
        MatrixIt iterator = new MatrixIt(input);
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenFirstEmptyThenHasNext() {
        int[][] input = {
                {}, {1}
        };
        MatrixIt iterator = new MatrixIt(input);
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenRowHasDiffSize() {
        int[][] input = {
                {1}, {2, 3}, {}, {}, {4}
        };
        MatrixIt iterator = new MatrixIt(input);
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenFewEmpty() {
        int[][] input = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt iterator = new MatrixIt(input);
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenEmpty() {
        int[][] input = {
                {}
        };
        MatrixIt iterator = new MatrixIt(input);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenEmptyThenNext() {
        int[][] input = {
                {}
        };
        MatrixIt iterator = new MatrixIt(input);
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiHasNext() {
        int[][] input = {
                {}, {1}
        };
        MatrixIt iterator = new MatrixIt(input);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenNoElements() {
        int[][] input = {
                {}, {}, {}
        };
        MatrixIt iterator = new MatrixIt(input);
        assertThat(iterator.hasNext()).isFalse();
    }
}