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
     * Инициализация пула (size) должна быть по количеству ядер в системе
     * Каждая нить запускает метод tasks.poll().
     */

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads.add(thread);
            thread.start();
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

    /**
     * Основной метод для проверки работы ThreadPool.
     * Создает пул потоков, запускает 5 задач, которые выводят сообщение в консоль,
     * а затем завершает работу пула.
     */

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.work(() -> System.out.println("Задача " + Thread.currentThread().getName()
                    + " выполняется."));
        }
        pool.shutdown();
    }
}


