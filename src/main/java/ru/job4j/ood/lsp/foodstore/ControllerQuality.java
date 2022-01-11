package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;
import java.util.List;

public class ControllerQuality {
    private List<Store> storeList;

    public ControllerQuality(List<Store> list) {
        this.storeList = list;
    }

    public boolean addFood(Food food) {
        for (Store store : storeList) {
            if (store.add(food)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Food food = new Food("Onion 1kg", LocalDate.of(2020, 8, 15),
                LocalDate.of(2022, 6, 15), 70);
        System.out.println(food.getPrice());
        Store store = new Warehouse();
        System.out.println(store.add(food));
        System.out.println(food.getPrice());
    }
}
