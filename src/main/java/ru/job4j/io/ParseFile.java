package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

/**
 * Необходимо поправить в коде следующие ошибки:
 * - Избавиться от get set за счет передачи File в конструктор
 * - Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
 * - Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
 * - Нарушен принцип единой ответственности. Тут нужно сделать два класса
 * - Методы getContent написаны в стиле копипаста. Нужно применить шаблон стратегия. content(Predicate<Character> filter)
 */

public class ParseFile {
    private File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        Predicate<Character> predicate = x -> true;
        return content(predicate);
    }

    public String getContentWithoutUnicode() throws IOException {
        Predicate<Character> predicate = x -> x < 0x80;
        return content(predicate);
    }

    public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream input = new FileInputStream(file)) {
            int data;
            while ((data = input.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
