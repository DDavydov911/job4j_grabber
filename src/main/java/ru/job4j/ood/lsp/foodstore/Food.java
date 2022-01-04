package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Food {
    private String name;
    private LocalDate createDate;
    private LocalDate expiryDate;
    private double price;
    private int discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public int getPercentLifeExpired() {
        double daysForStore = (double) ChronoUnit.DAYS.between(createDate, expiryDate);
        double daysAfterCreated = (double) ChronoUnit.DAYS.between(createDate, LocalDate.now());
        int percentLifeExpired = (int) Math.round(daysAfterCreated / daysForStore * 100);
        return Math.min(percentLifeExpired, 100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getPrice() {
        return price * (100 - discount) / 100;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public static void main(String[] args) {
        LocalDate cr = LocalDate.of(2021, 12, 1);
        LocalDate ex = LocalDate.of(2021, 12, 31).plusDays(1);
        double daysForStore = (double) ChronoUnit.DAYS.between(cr, ex);
        double daysBeforeExpiry = (double) ChronoUnit.DAYS.between(LocalDate.now(), ex);
        System.out.println(daysForStore);
        System.out.println(daysBeforeExpiry);
        System.out.println(daysBeforeExpiry / daysForStore * 100);
        Food food = new Food("bread", cr, ex, 87);
        System.out.println(food.getPercentLifeExpired());
    }

    @Override
    public String toString() {
        return "Food{" + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expiryDate=" + expiryDate
                + ", price=" + price
                + ", discount=" + discount
                + ", expired=" + getPercentLifeExpired() + "%"
                + '}';
    }
}
