package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;
import java.util.List;

public class ControllerQuality {
    private List<FoodStore> storeList;

    public ControllerQuality(List<FoodStore> list) {
        this.storeList = list;
    }

    public void addFood(Food food) {
        for (int i = 0; i < storeList.size() - 1; i++) {
            FoodStore store = storeList.get(i);
            for (int j = 0; j < store.getList().size(); j++) {
                if (store.add(food)) {
                    break;
                }
            }
        }
    }
}
