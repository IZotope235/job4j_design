package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
class SimpleSet2Test {
    @Test
    void when4AddAndAddFirstThenFalse() {
        SimpleSet2 set = new SimpleSet2();
        set.add("first");
        set.add("second");
        set.add("third");
        set.add("four");
        assertThat(set.add("first")).isFalse();
    }
}