package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

/**
 * Класс AccountStorage служит для хранения счетов пользователей. Здесь можно добавлять новых пользователей,
 * обновлять данные, удалять пользователей и осуществлять денежные переводы.
 * Доступ ко всем методам класса AccountStorage должен быть эксклюзивным для этого нужно использовать синхронизацию.
 * Поле accounts - это общий ресурс для нитей, поэтому этот объект используется в качестве объекта монитора.
 * Метод transfer должен перевести сумму amount со счета fromId в счет toId, т.е. решается классическая задача
 * по переводу денег с одного счета на другой. В этом задании нужно сделать блокирующий кеш AccountStorage для модели Account.
 */

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public boolean add(Account account) {
        return false;
    }

    public boolean update(Account account) {
        return false;
    }

    public void delete(int id) {

    }

    public Optional<Account> getById(int id) {
        return Optional.empty();
    }

    public boolean transfer(int fromId, int toId, int amount) {
        return false;
    }
}
