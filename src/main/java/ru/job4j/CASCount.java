package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int expectedValue;
        int newValue;

        do {
            expectedValue = count.get();
            newValue = expectedValue + 1;

            if (newValue < 0) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
        } while (!count.compareAndSet(expectedValue, newValue));
    }

    public int get() {
        return count.get();
    }
}
