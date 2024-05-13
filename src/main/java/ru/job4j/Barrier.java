package ru.job4j;

/**
 * Метод on и off меняют флаг с true на false. После каждого изменения программа будит нити, которые ждут изменений.
 * Синхронизация и методы notifyAll и wait вызываются у объекта класса Barrier.
 * Когда нить заходит в метод check, то она проверяет flag. Если флаг = false, то нить засыпает.
 * Когда другая нить выполнит метод on или off, то у монитора выполняется метод notifyAll.
 * Метод notifyAll переводит все нити из состояния wait в runnable.
 * Чтобы избежать проблем с согласованностью данных, метод wait всегда вызывается в цикле while, а не в условном блоке if.
 */

public class Barrier {
    private boolean flag = false;

    private final Object monitor = this;

    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
