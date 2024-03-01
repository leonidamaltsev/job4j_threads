package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int index = 1; index <= 100; index++) {
                            System.out.print("\rLoading : " + index  + "%");
                            Thread.sleep(1000);
                            System.out.println();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
