package ru.job4j.io.piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Создаются объекты классов PipedInputStream и PipedOutputStream. Соединение ввода и вывода
 * производится с помощью метода connect(). Порядок соединения элементов не важен. В нити firstThread
 * мы записываем строку:("Job4j".getBytes()). В метод write мы передаём строку, превращённую в байты.
 * Далее в нити secondThread считываем строку: while ((character = input.read()) != -1). Считывание
 * происходит побайтово, в выводе каждый байт приводится к типу char для превращения байтов в символы.
 * Открытые потоки закрываются с помощью метода close() в каждой из нитей после выполнения нитью всей
 * полезной работы.
 */
public class PipedUsage {

    public static void main(String[] args) throws IOException {

        final PipedInputStream input = new PipedInputStream();
        final PipedOutputStream output = new PipedOutputStream();

        Thread firstThread = new Thread(() -> {
            try {
                output.write("Job4j".getBytes());
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread secondThread = new Thread(() -> {
            try {
                int character;
                while ((character = input.read()) != -1) {
                    System.out.print((char) character);
                }
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        input.connect(output);
        firstThread.start();
        secondThread.start();
    }
}
