package ru.job4j.ref;

/**
 * cache.add(user) - Добавляем объект в кеш;
 * user.setName("first") - Редактируем объект по ссылке;
 * System.out.println(cache.findById(1).getName()) - Получаем значение.
 */

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("main");
        User user1 = User.of("user1");
        cache.add(user);
        cache.add(user1);
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        user.setName("first");
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        user.setName("second");
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(cache.findById(1).getName());
        System.out.println(cache.findAll());
    }
}
