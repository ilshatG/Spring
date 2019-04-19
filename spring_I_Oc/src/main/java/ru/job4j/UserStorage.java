package ru.job4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class UserStorage implements Storage {
    private final Storage storage;

//    @Autowired
    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }

    @Override
    public void update(User user) {
        storage.update(user);
    }

    @Override
    public void delete(User user) {
        storage.delete(user);
    }

    @Override
    public User findById(User user) {
        return storage.findById(user);
    }

    public List<User> getAll() {
        return storage.getAll();
    }
}
