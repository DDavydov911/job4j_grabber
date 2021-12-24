package ru.job4j.srp;

import java.util.List;
/*
Хранилище одновременно выводит на печать,
это не  его зона ответственности
 */
public class WrongSRP3<E> {
    private List<E> list;

    public boolean add() {
        return false;
    }

    public Object get() {
        return null;
    }

    public boolean remove() {
        return false;
    }

    public void print() {
        for (E e : list) {
            System.out.println(e);
        }
    }
}
