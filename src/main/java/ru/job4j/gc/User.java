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
    private int id;
    private String name;
    private char sex;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", id, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
