package ru.job4j.ood.lsp.parking;

public interface Parking {

    boolean placeCarOnParking(Car car);

    boolean contains(Car car);
}
