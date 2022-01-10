package ru.job4j.ood.lsp.parking;

public class SmallCar implements Car {
    private final int size;

    public SmallCar() {
        this.size = 1;
    }

    @Override
    public int getSize() {
        return size;
    }
}
