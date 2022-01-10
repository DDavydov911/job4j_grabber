package ru.job4j.ood.lsp.parking;

public class SmallCar implements Car {
    private final int size;

    private Parking parking = null;

    public SmallCar(int size) {
        if (size != 1) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
