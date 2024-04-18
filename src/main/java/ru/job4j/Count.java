package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * аннотация: @ThreadSafe говорит пользователям данного класса,
 * что класс можно использовать в многопоточном режиме и он будет работать правильно
 * аннотация: @GuardedBy("this") выставляется над общим ресурсом. Аннотация имеет входящий параметр.
 * Он указывает на монитор, по которому мы будем синхронизироваться. Надо работать с этим ресурсом
 * только в критической секции, которая синхронизируется по монитору, указанный в аннотации.
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}
