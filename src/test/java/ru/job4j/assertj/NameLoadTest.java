package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenNamesThenParseSuccess() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1=one", "2=two", "3=three"};
        nameLoad.parse(names);
        var result = nameLoad.getMap();
        var expected = Map.of(
                "1", "one",
                "2", "two",
                "3", "three");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenNamesEmptyThenParseException() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void whenNameDoesNotContainEquallySymbolThenException() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1=one", "2two", "3=three"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("2two");
    }

    @Test
    void whenEquallySymbolIsFirstThenException() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1=one", "=two", "3=three"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=two");
    }

    @Test
    void whenEquallySymbolIsLastThenException() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1=one", "2=", "3=three"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("2=");
    }

}