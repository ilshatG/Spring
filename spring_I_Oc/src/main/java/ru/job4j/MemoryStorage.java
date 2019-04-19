package ru.job4j;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MemoryStorage implements Storage {
    Map<Long, User> values = new HashMap<>();
    @Override
    public void add(User user) {
        values.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        values.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        values.remove(user.getId());
    }

    @Override
    public User findById(User user) {
        return values.get(user.getId());
    }

    @Override
    public List<User> getAll() {
        return values.values().stream().collect(Collectors.toList());
    }
}
