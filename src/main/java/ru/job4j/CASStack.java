package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Чтобы сделать класс Stack потокобезопасным, используется класс java.util.concurrent.atomic.AtomicReference.
 * Этот класс поддерживает CAS (compare-and-swap) операции. Здесь нет синхронизации, но класс потокобезопасный, м вот почему.
 * Вначале мы считываем указатель - ref = head.get()
 * Указатель могут считать несколько нитей одновременно.
 * Мы не блокируем эту операцию - } while (!head.compareAndSet(ref, temp));
 * Метод compareAndSet атомарный. Это значит если две нити прочитали одно и то же значение ref,
 * то первый вызов compareAndSet даст true, а второй вызов вернет false.
 * Вторая нить будет заново читать ref и выполнять операцию compareAndSet.
 * Обе нити не блокируются, а выполняются параллельно.
 */

@ThreadSafe
public class CASStack<T> {

    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        Node<T> ref;
        do {
            ref = head.get();
            temp.next = ref;
        } while (!head.compareAndSet(ref, temp));
    }

    public T poll() {
        Node<T> ref;
        Node<T> temp;
        do {
            ref = head.get();
            if (ref == null) {
                throw new IllegalStateException("Stack is empty");
            }
            temp = ref.next;
        } while (!head.compareAndSet(ref, temp));
        ref.next = null;
        return ref.value;
    }

    private static final class Node<T> {
        final T value;

        Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }
}
