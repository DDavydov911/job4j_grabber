package ru.job4j.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CityParkingTest {

    @Ignore
    @Test
    public void whenParkSmallCar() {
        Car car = new SmallCar(1);
        Parking parking = new CityParking(3, 3);
        parking.placeCarOnParking(car);
        assertTrue(parking.contains(car));
    }

    @Ignore
    @Test
    public void whenParkBigCar() {
        Car car = new BigCar(4);
        Parking parking = new CityParking(3, 3);
        parking.placeCarOnParking(car);
        assertTrue(parking.contains(car));
    }

}