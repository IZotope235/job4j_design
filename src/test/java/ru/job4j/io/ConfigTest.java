package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLine() {
        String path = "./data/pair_with_comment_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Marat");
    }

    @Test
    void whenPairWithTwoEquals() {
        String path = "./data/pair_with_two_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Marat=");
    }

    @Test
    void whenPairWithoutKeyAndValue() {
        String path = "./data/pair_without_key_and_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWithoutEqualSymbol() {
        String path = "./data/pair_without_equal_symbol.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWithEqualSymbolNotFirst() {
        String path = "./data/pair_with_hash_symbol_not_first.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Marat#");
    }
}