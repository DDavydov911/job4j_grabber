package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {

    List<Food> getListAndClear();

    List<Food> getList();

    boolean add(Food food);

    boolean delete(Food food);

    boolean accept(Food food);

    default int getPercentLifeExpired(Food food) {
        double daysForStore = (double) ChronoUnit.DAYS.between(
                food.getCreateDate(), food.getExpiryDate());
        double daysAfterCreated = (double) ChronoUnit.DAYS.between(
                food.getCreateDate(), LocalDate.of(2022, 1, 11));
        int percentLifeExpired = (int) Math.round(daysAfterCreated / daysForStore * 100);
        return Math.min(percentLifeExpired, 100);
    }
}
