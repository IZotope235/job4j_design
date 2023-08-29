package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "six");
        assertThat(list).isNotEmpty()
                .hasSize(6)
                .contains("six")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first")
                .allMatch(el -> el.length() < 10);
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "three");
        assertThat(map).isNotEmpty()
                .hasSize(3)
                .containsKeys("one", "two", "three")
                .containsValues(0, 1, 2)
                .doesNotContainKey("four")
                .doesNotContainValue(3)
                .containsEntry("one", 0)
                .allSatisfy((e, v) -> assertThat(v).isLessThan(3));

    }

}