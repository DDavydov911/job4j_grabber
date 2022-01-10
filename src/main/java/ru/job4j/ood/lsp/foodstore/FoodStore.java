package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface FoodStore {

    List<Food> getList();

    boolean add(Food food);

    boolean delete(Food food);

    boolean accept(Food food);

    default int getPercentLifeExpired(Food food) {
        double daysForStore = (double) ChronoUnit.DAYS.between(
                food.getCreateDate(), food.getExpiryDate());
        double daysAfterCreated = (double) ChronoUnit.DAYS.between(
                food.getCreateDate(), LocalDate.now());
        int percentLifeExpired = (int) Math.round(daysAfterCreated / daysForStore * 100);
        return Math.min(percentLifeExpired, 100);
    }
}
