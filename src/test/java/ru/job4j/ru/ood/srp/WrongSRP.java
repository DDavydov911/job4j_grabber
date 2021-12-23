package ru.job4j.ru.ood.srp;

import java.util.ArrayList;
    /*
    Один класс должен отвечать за одну область.
    В данном случае, у нас класс призванный что-то хранить,
    однако здесь же метод что-то проанализировать. Это разные области.
    И тут же метод инициализации. Нет методов для поиска, замены и удаления
    хранящихся элементов.
    Кроме того вместо интерфейса хранилища используется ArrayList,
    что делает код негибким.
     */
public class WrongSRP {

    private ArrayList<Integer> list;

    public void addThat() {
    }

    public Integer analyze() {
        return null;
    }

    public void init() {
    }
}
