package ru.job4j.ood.lsp.foodstore;

import org.junit.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControllerQualityTest {

    @Test
    public void whenPutExpired17PercentInWarehouseThenTrue() {
    Store store = new Warehouse();
    assertTrue(store.add(new Food("Milk 2,5%", LocalDate.of(2022, 1, 3),
            LocalDate.of(2022, 2, 20), 83)));
    }

    @Test
    public void whenPutExpired58PercentInWarehouseThenFalse() {
        Store store = new Warehouse();
        assertFalse(store.add(new Food("Rice 800g", LocalDate.of(2021, 6, 15),
                LocalDate.of(2022, 6, 15), 75)));
    }

    @Test
    public void whenPutExpired58PercentInShopThenTrue() {
        Store store = new Shop();
        assertTrue(store.add(new Food("Rice 800g", LocalDate.of(2021, 6, 15),
                LocalDate.of(2022, 6, 15), 75)));
    }

    @Test
    public void whenPutExpired17PercentInShopThenFalse() {
        Store store = new Shop();
        assertFalse(store.add(new Food("Milk 2,5%", LocalDate.of(2022, 1, 3),
                LocalDate.of(2022, 2, 20), 83)));
    }

    @Test
    public void whenPutExpired77PercentInShopThenPriceIs42() {
        Store store = new Shop();
        Food food = new Food("Onion 1kg", LocalDate.of(2020, 8, 15),
                LocalDate.of(2022, 6, 15), 70, 40);
        store.add(food);
        double expected = 42d;
        assertEquals(expected, food.getPrice(), 0);
    }

    @Test
    public void whenPutExpired100PercentInTrashThenTrue() {
        Store store = new Trash();
        assertTrue(store.add(new Food("Milk 0,5%", LocalDate.of(2021, 12, 15),
                LocalDate.of(2021, 12, 22), 70)));
    }

    @Test
    public void whenPutExpired17PercentInTrashThenFalse() {
        Store store = new Trash();
        assertFalse(store.add(new Food("Milk 2,5%", LocalDate.of(2022, 1, 3),
                LocalDate.of(2022, 2, 20), 83)));
    }

    @Test
    public void whenAdd17PercentFoodExInControlQualityThenTrue() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> list = new ArrayList<>();
        list.add(warehouse);
        list.add(shop);
        list.add(trash);
        ControllerQuality cq = new ControllerQuality(list);
        Food food17percent = new Food("Milk 2,5%", LocalDate.of(2022, 1, 3),
                LocalDate.of(2022, 2, 20), 83);
        assertTrue(cq.addFood(food17percent));
        assertTrue(warehouse.getList().contains(food17percent));
    }

    @Test
    public void whenAdd58PercentFoodExInControlQualityThenTrue() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> list = new ArrayList<>();
        list.add(warehouse);
        list.add(shop);
        list.add(trash);
        ControllerQuality cq = new ControllerQuality(list);
        Food food58percent = new Food("Rice 800g", LocalDate.of(2021, 6, 15),
                LocalDate.of(2022, 6, 15), 75);
        assertTrue(cq.addFood(food58percent));
        assertTrue(shop.getList().contains(food58percent));
    }

    @Test
    public void whenAdd100PercentFoodExInControlQualityThenTrue() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> list = new ArrayList<>();
        list.add(warehouse);
        list.add(shop);
        list.add(trash);
        ControllerQuality cq = new ControllerQuality(list);
        Food food100percent = new Food("Milk 0,5%", LocalDate.of(2021, 12, 15),
                LocalDate.of(2021, 12, 22), 70);
        assertTrue(cq.addFood(food100percent));
        assertTrue(trash.getList().contains(food100percent));
    }

    @Test
    public void whenAddFoodInWarehouseThenResortToTrash() {
        Store warehouse = new Warehouse();
        Food food = new Food("Milk 2,5%", LocalDate.of(2022, 1, 3),
                LocalDate.of(2022, 2, 20), 83);
        warehouse.add(food);
        food.setExpiryDate(LocalDate.of(2022, 1, 10));
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> list = new ArrayList<>();
        list.add(warehouse);
        list.add(shop);
        list.add(trash);
        ControllerQuality cq = new ControllerQuality(list);
        cq.resort();
        assertEquals(food, trash.getList().get(0));
    }

    @Test
    public void whenAddFoodInTrashThenResortToShop() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        Food food = new Food("Milk 0,5%", LocalDate.of(2021, 12, 15),
                LocalDate.of(2021, 12, 22), 70);
        trash.add(food);
        food.setExpiryDate(LocalDate.of(2022, 2, 10));
        List<Store> list = new ArrayList<>();
        list.add(warehouse);
        list.add(shop);
        list.add(trash);
        ControllerQuality cq = new ControllerQuality(list);
        cq.resort();
        assertEquals(food, shop.getList().get(0));
    }

    @Test
    public void whenAddFoodInShopThenNoResort() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Food food = new Food("Milk 2,5%", LocalDate.of(2022, 1, 3),
                LocalDate.of(2022, 1, 20), 83);
        shop.add(food);
        Store trash = new Trash();
        List<Store> list = new ArrayList<>();
        list.add(warehouse);
        list.add(shop);
        list.add(trash);
        ControllerQuality cq = new ControllerQuality(list);
        cq.resort();
        assertEquals(food, shop.getList().get(0));
    }
}