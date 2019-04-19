package ru.job4j;

import java.util.Arrays;

public class User {
    private long id;
    private String name;

    public User(String name) {
        this((long)(Math.random()*1_000_000), name);
    }

    public User() {
        this("");
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {this.name});
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                name.equals(((User)obj).getName());
    }

    @Override
    public String toString() {
        return " " + this.id + " , " + this.name + "; ";
    }
}
