package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * ThreadPool - хранилище для ресурсов, которые можно переиспользовать,
 * и после выполнения работы, вернуть обратно в пул
 */

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    /**
     * Конструктор ThreadPool.
     * Здесь создаются нити в количестве size и добавляются в threads.
     * Каждая нить запускает метод tasks.poll().
     */

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        tasks.poll();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    /**
     * Метод добавляет задачи в блокирующую очередь tasks
     * @param job Задача, которую нужно добавить
     */

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    /**
     * Метод завершает все запущенные задачи
     */

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
