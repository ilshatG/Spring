package ru.job4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ImportUser {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-jdbc.xml");
        UserStorage users = context.getBean(UserStorage.class);
        users.add(new User("The user"));
        users.add(new User("Judas Priest"));
        System.out.println(users.getAll());
    }
}
