package ru.job4j.ood.lsp.parking;

public class BigCar implements Car {
    private int size;

    private Parking parking;

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

    @Override
    public void takeParking(Parking parking) {
        this.parking = parking;
        parking.setCar(this);
    }

    @Override
    public void freeParking() {

    }
}
