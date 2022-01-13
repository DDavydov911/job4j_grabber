package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> list = new ArrayList<>();

    @Override
    public List<Food> getList() {
        return list;
    }

    public boolean addToList(Food food) {
        return list.add(food);
    }

    @Override
    public boolean add(Food food) {
        if (accept(food) && !list.contains(food)) {
            list.add(food);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Food food) {
        Food res = null;
        if (list.contains(food)) {
            list.remove(food);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean accept(Food food) {
        double percent = getPercentLifeExpired(food);
        return percent < 25;
    }
}
