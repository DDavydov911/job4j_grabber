package ru.job4j.ood.lsp.parking;

public class BigCar implements Car {
    private final int size;

    public BigCar(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}