package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenNameIsPetr() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        var result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Petr");
    }

    @Test
    void whenAddAndFindThenRoleIsProgrammer() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        var result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Programmer");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        var result = store.findById("5");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateIdAndFindRoleIsProgrammer() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        store.add(new Role("1", "Petr", "Artist"));
        var result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Programmer");
    }

    @Test
    void whenReplaceThenRoleIsArtist() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        store.replace("1", new Role("1", "Petr", "Artist"));
        var result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Artist");
    }
    @Test
    void whenReplaceThenTrue() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        var result = store.replace("1", new Role("1", "Petr", "Artist"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceThenFalse() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        var result = store.replace("2", new Role("1", "Petr", "Artist"));
        assertThat(result).isFalse();
    }

    @Test
    void whenNoReplaceUserThenNoChangeRole() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        store.replace("2", new Role("2", "Petr", "Artist"));
        var result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Programmer");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        store.delete("1");
        var result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenRoleIsProgrammer() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        store.delete("10");
        var result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Programmer");
    }

    @Test
    void whenFindByIdIsSuccess() {
        var store = new RoleStore();
        var expected = new Role("1", "Petr", "Programmer");
        store.add(expected);
        var result = store.findById("1");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenFindByIdIsNull() {
        var store = new RoleStore();
        store.add(new Role("1", "Petr", "Programmer"));
        var result = store.findById("2");
        assertThat(result).isNull();
    }


}