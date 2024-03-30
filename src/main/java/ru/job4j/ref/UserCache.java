package ru.job4j.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Чтобы метод findAll() избавился от общих ресурсов, и работал с копиями объекта User,
 * перебираем в цикле users.values(), создаем локальные копии объектов и возвращаем их в виде списка.
 */

public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        for (User user : users.values()) {
            userList.add(User.of(user.getName()));
        }
        return userList;
    }
}
