package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ArgsNameTest {

    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenKeyNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("This key: 'Xms' is missing");
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Arguments not passed to program");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-=?msg=Exit=' does not contain a key");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=?msg=Hello="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-=?msg=Hello=' does not contain a key");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown3() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=value"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-=value' does not contain a key");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-request="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-request=' does not contain a value");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-encoding="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-encoding=' does not contain a value");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown3() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-a="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-a=' does not contain a value");
    }

    @Test
    void whenThereNoEqualSignThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-request?msgHello"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-request?msgHello' does not contain an equal sign");
    }

    @Test
    void whenThereNoEqualSignThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-encodingUTF-8"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-encodingUTF-8' does not contain an equal sign");
    }

    @Test
    void whenNoHyphenPrefixThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "request=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument 'request=?msg=Exit=' does not start with a '-' character");
    }

    @Test
    void whenNoHyphenPrefixThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"Xmx=512", "-request=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument 'Xmx=512' does not start with a '-' character");
    }
}