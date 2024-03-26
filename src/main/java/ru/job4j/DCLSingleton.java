package ru.job4j;

public class DCLSingleton {

    /**
     * Использовать Double Checked Lock без volatile крайне опасно.
     * Без volatile может возникнуть ситуация, когда другой поток может получить
     * и начать использовать (на основании условия, что указатель не нулевой)
     * незавершенный экземпляр класса. Double Checked Lock можно использовать без
     * исключений с immutable объектами (String, Integer, Float, и т.д.)
     */

    private static volatile DCLSingleton instance;

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

    private DCLSingleton() {
    }
}
