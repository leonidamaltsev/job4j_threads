package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * Сервис для рассылки почты.
 * Использует пул потоков для выполнения задач по отправке email.
 */
public class EmailNotification {
    private final ExecutorService pool = Executors.newCachedThreadPool();

    /**
     * Отправляет email-уведомление указанному пользователю.
     * @param user Пользователь, которому необходимо отправить уведомление
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification {%s} to email {%s}.",
                    user.getUsername(), user.getEmail());
            String body = String.format("Add a new event to {username}.",
                    user.getUsername());
            user.send(subject, body, user.getEmail());
        });
    }

    /**
     * Закрывает пул потоков и завершает работу класса.
     */
    public void close() {
        pool.shutdownNow();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(new User("Roman", "test@gmail.com"));
        emailNotification.emailTo(new User("Olga", "test@gmail.com"));
        emailNotification.emailTo(new User("Marina", "test@gmail.com"));
        emailNotification.emailTo(new User("Anna", "test@gmail.com"));
        emailNotification.emailTo(new User("Elena", "test@gmail.com"));
        emailNotification.emailTo(new User("Ben", "test@gmail.com"));
        emailNotification.emailTo(new User("Donald", "test@gmail.com"));
        emailNotification.close();
    }
}
