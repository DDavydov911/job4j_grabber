package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements FoodStore {

    private List<Food> list = new ArrayList<>();

    public List<Food> getList() {
        return list;
    }

    @Override
    public boolean add(Food food) {
        if (list.contains(food)) {
            return false;
        } else {
            list.add(food);
            return true;
        }
    }

    @Override
    public boolean delete(Food food) {
        if (list.contains(food)) {
            list.remove(food);
            return true;
        } else {
            return false;
        }
    }
}
