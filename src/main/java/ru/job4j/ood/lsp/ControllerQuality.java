package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class ControllerQuality {
    private FoodStore warehouse;
    private FoodStore shop;
    private FoodStore trash;

    public boolean getTransfer(Food food, FoodStore storeFrom, FoodStore storeTo) {
        return storeFrom.delete(food) && storeTo.add(food);
    }

    public void checkFoodForTransfer(Food food, FoodStore storeFrom,
                                     FoodStore storeTo, Predicate<Food> predicate) {
        if (predicate.test(food)) {
            getTransfer(food, storeFrom, storeTo);
        }
    }

    public void checkFoodForDiscount(Food food, Predicate<Food> predicate, int discount) {
        if (predicate.test(food)) {
            food.setDiscount(discount);
        }
    }

    public void checkStore(
            FoodStore storeCheck, FoodStore storeTo1, Predicate<Food> predicate1,
            FoodStore storeTo2, Predicate<Food> predicate2, Predicate<Food> predicate3, int discount
    ) {
        List<Food> list = storeCheck.getList();
        if (list.size() > 0) {
            for (int i = list.size() - 1; i >= 0; i--) {
                Food temp = list.get(i);
                checkFoodForDiscount(temp, predicate3, discount);
                checkFoodForTransfer(temp, storeCheck, storeTo1, predicate1);
                checkFoodForTransfer(temp, storeCheck, storeTo2, predicate2);
            }
        } else {
            System.out.println("Пустое хранилище:" + storeCheck);
        }
    }

    public void checkAllStores(int discount) {
        checkStore(warehouse,
                trash, food -> food.getPercentLifeExpired() == 100,
                shop, food -> food.getPercentLifeExpired() < 100
                && food.getPercentLifeExpired() >= 25,
                food -> food.getPercentLifeExpired() > 75, discount);
        checkStore(shop,
                warehouse, food -> food.getPercentLifeExpired() < 25,
                trash, food -> food.getPercentLifeExpired() == 100,
                food -> food.getPercentLifeExpired() > 75, discount);
        checkStore(trash,
                warehouse, food -> food.getPercentLifeExpired() < 25,
                shop, food -> food.getPercentLifeExpired() >= 25
                        && food.getPercentLifeExpired() < 100,
                food -> food.getPercentLifeExpired() > 75, discount);
    }

    public void setWarehouse(FoodStore warehouse) {
        this.warehouse = warehouse;
    }

    public void setShop(FoodStore shop) {
        this.shop = shop;
    }

    public void setTrash(FoodStore trash) {
        this.trash = trash;
    }

    public static void main(String[] args) {
        FoodStore warehouse = new Warehouse();
        FoodStore shop = new Shop();
        FoodStore trash = new Trash();
        warehouse.add(
                new Food("Dark bread", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2022, 1, 30), 87));
        warehouse.add(
                new Food("White bread", LocalDate.of(2021, 12, 11),
                        LocalDate.of(2022, 1, 10), 80));
        warehouse.add(
                new Food("Milk 3,5%", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2022, 1, 30), 99));
        shop.add(
                new Food("Milk 2,5%", LocalDate.of(2021, 12, 30),
                        LocalDate.of(2022, 1, 12), 83));
        shop.add(
                new Food("Milk 1,5%", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 12, 30), 78));
        shop.add(
                new Food("Rice 800g", LocalDate.of(2021, 6, 15),
                        LocalDate.of(2022, 6, 15), 75));
        shop.add(
                new Food("Potato 1kg", LocalDate.of(2021, 10, 15),
                        LocalDate.of(2022, 10, 15), 60));
        trash.add(
                new Food("Onion 1kg", LocalDate.of(2020, 8, 15),
                        LocalDate.of(2022, 6, 15), 70));
        trash.add(
                new Food("Milk 0,5%", LocalDate.of(2021, 12, 15),
                        LocalDate.of(2021, 12, 22), 70));
        trash.add(
                new Food("Orange", LocalDate.of(2021, 9, 10),
                        LocalDate.of(2022, 4, 10), 100));

        System.out.println("Warehouse before transfers: ");
        for (Object o : warehouse.getList()) {
            System.out.println(o);
        }
        System.out.println("Shop before transfers: ");
        for (Object o : shop.getList()) {
            System.out.println(o);
        }
        System.out.println("Trash before transfers: ");
        for (Object o : trash.getList()) {
            System.out.println(o);
        }
        ControllerQuality controllerQuality = new ControllerQuality();
        controllerQuality.setWarehouse(warehouse);
        controllerQuality.setShop(shop);
        controllerQuality.setTrash(trash);
        controllerQuality.checkAllStores(50);
        System.out.println("\nWarehouse after transfers: expired less 25%");
        for (Object o : warehouse.getList()) {
            System.out.println(o);
        }
        System.out.println("Shop after transfers: expired less 100%");
        for (Object o : shop.getList()) {
            System.out.println(o);
        }
        System.out.println("Trash after transfers: expired 100%");
        for (Object o : trash.getList()) {
            System.out.println(o);
        }
    }
}
