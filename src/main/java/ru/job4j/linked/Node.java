package ru.job4j.linked;

/**
 * Поля next и value объявлены final. Убраны сеттеры, чтобы предотвратить изменение значений после создания объекта.
 * Теперь каждый объект класса `Node<T>` будет неизменяемым, что обеспечивает класс Immutable.
 */

public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
