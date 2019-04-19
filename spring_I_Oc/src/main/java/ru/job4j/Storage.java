package ru.job4j;

import java.util.List;

public interface Storage {
    public void add(User user);
    public void update(User user);
    public void delete(User user);
    public User findById(User user);
    public List<User> getAll();
}
