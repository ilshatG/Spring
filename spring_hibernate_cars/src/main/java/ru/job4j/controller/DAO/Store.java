package ru.job4j.controller.DAO;


import ru.job4j.models.Ad;
import ru.job4j.models.User;

import java.util.List;
import java.util.Map;


interface Store {
    <T> boolean add(T t);
    //void add(Ad item);
    void update(Ad item);
    void delete(Ad item);
    Map<Long, Ad> getAll();
    User currentUser(String login, String password);
    List getListFromSQL(String query);
    <T> List<T> getListFromSQL(String query, Class<T> tClass);
    public <T> T get(T t, long id);
}
