package ru.job4j;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    public UserStorage getUserStorage() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-memory.xml");
        UserStorage memory = context.getBean(UserStorage.class);
        return memory;
    }

//    @Test
//    public void whenAddUserToStorageShouldSaveIt() {
//        MemoryStorage memoryStorage = new MemoryStorage();
//        UserStorage storage = new UserStorage(memoryStorage);
//        storage.add(new User());
//    }

    @Test
    public void whenLoadContextShouldGetBeans() {

        UserStorage memory = getUserStorage();
        assertNotNull(memory);
    }

    @Test
    public void whenAddUserShouldContainIt() {
        UserStorage userStorage = getUserStorage();
        User user = new User("Hall");
        userStorage.add(user);
        User foundUser = userStorage.findById(user);
        assertNotNull(foundUser);
        assertThat(userStorage.findById(user).equals(user),is(true));
    }

    @Test
    public void whenDeleteUserShouldNOtContainIt() {
        UserStorage userStorage = getUserStorage();
        User user = new User("Hall");
        userStorage.add(user);
        userStorage.delete(user);
        User foundUser = userStorage.findById(user);
        assertNull(foundUser);
    }

    @Test
    public void whenUpdateUserShouldContainIt() {
        UserStorage userStorage = getUserStorage();
        User user = new User("Hall");
        userStorage.add(user);
        User user2 = new User(user.getId(), "Vincent");
        userStorage.update(user2);
        User foundUser = userStorage.findById(user2);
        assertNotNull(foundUser);
        assertThat(userStorage.findById(user2).equals(user2),is(true));
    }
}