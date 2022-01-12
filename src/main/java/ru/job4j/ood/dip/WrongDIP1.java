package ru.job4j.ood.dip;

import ru.job4j.ood.lsp.foodstore.Food;

import java.util.ArrayList;
import java.util.List;

public class WrongDIP1 {
    /*
        Класс зависит от реализации хранилища.
        Правильно использовать интерфейс хранилища? Например, interface Store.
    */
    private List<Food> list = new ArrayList<>();

    /*
        Класс зависит от переменной конкретного типа, переданной в качестве аргумента.
        Правильно использовать интерфейс продукта, например interface Food.
     */
    public void add(Milk milk) {
    }

    public boolean remove() {
        return false;
    }

    /*
        Класс зависит от конкретного типа возвращаемого объекта.
        Правильно использовать интерфейс и возвращать уже требуемый тип объекта,- тот же Food.
     */
    public Milk getFood() {
        return new Milk();
    }

    private class Milk {
    }
}
