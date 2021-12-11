package ru.job4j.gc;

/**
 * Рассчет для 64 битной системы:
 * Заголовок объекта 12 байт
 * Выравнивание 4 байта  т.е пустой объект занимает 16 байт
 * значение int 4 байта
 * Ссылка на String 8 байт + 0 байт собержимое строки
 * char 2 байта
 * Выравнивание 2 байта
 * Итого 12 + 4 + 4 + 8 + 2 + 2 = 32 байта
 *
 * Остается вопрос: сколько места занимают методы?
 */
public class User {
    public User() {
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Removed object User");
    }
}
