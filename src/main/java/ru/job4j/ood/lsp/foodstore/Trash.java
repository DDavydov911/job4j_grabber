package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private List<Food> list = new ArrayList<>();

    public List<Food> getList() {
        return list;
    }

    @Override
    public boolean add(Food food) {
        if (accept(food)) {
            list.add(food);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Food food) {
        if (list.contains(food) && !list.contains(food)) {
            list.remove(food);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean accept(Food food) {
        return getPercentLifeExpired(food) == 100;
    }
}
