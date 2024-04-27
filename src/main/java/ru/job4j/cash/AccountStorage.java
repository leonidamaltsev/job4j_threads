package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

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

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Account fromAccount = accounts.get(fromId);
        Account toAccount = accounts.get(toId);
        if (fromAccount == null || toAccount == null || fromAccount.amount() < amount) {
            return false;
        }
        Account updatedFromAccount = new Account(fromId, fromAccount.amount() - amount);
        Account updatedToAccount = new Account(toId, toAccount.amount() + amount);
        accounts.replace(fromId, updatedFromAccount);
        accounts.replace(toId, updatedToAccount);
        return true;
    }
        
}
