package ru.job4j;

import java.io.*;

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

    public synchronized void setFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        InputStream input = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = input.read()) > 0) {
            output += (char) data;
        }
        return output;
    }

    public String getContentWithoutUnicode() throws IOException {
        InputStream input = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = input.read()) > 0) {
            if (data < 0x80) {
                output += (char) data;
            }
        }
        return output;
    }

    public void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i++) {
            o.write(content.charAt(i));
        }
    }
}
