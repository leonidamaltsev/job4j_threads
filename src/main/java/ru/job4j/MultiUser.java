package ru.job4j;

/**
 * Также нить может проснуться без уведомления, прерывания или тайм-аута - это называется ложное пробуждение.
 * Хотя на практике это происходит редко, но все равно нужно пресекать шанс самовольного пробуждения нити
 * путём проверки условия в цикле while, и пробуждать нить только, когда условие выполнится.
 */

public class MultiUser {
    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        master.start();
        slave.start();
    }
}

