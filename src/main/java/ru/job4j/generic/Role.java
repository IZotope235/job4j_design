package ru.job4j.generic;

public class Role extends Base {
    private final String name;
    private final String role;

    public Role(String id, String name, String role) {
        super(id);
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

