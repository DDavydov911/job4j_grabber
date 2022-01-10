package ru.job4j.ood.lsp.foodstore;

import java.util.List;

public interface FoodStore {

    List getList();

    boolean add(Food food);

    boolean delete(Food food);
}