package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private List<Food> list = new ArrayList<>();

    public List<Food> getList() {
        return list;
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
        if (percent > 75 && percent < 100) {
            food.setPrice((100d - food.getDiscount()) / 100 * food.getPrice());
        }
        return percent >= 25 && percent < 100;
    }
}
