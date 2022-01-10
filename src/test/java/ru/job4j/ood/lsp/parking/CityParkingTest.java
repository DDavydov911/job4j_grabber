package ru.job4j.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CityParkingTest {

    @Ignore
    @Test
    public void whenPark2SmallCarFor1PlaceThenTrueAndFalse() {
        Car car1 = new SmallCar();
        Car car2 = new SmallCar();
        Parking parking = new CityParking(1, 0);
        assertTrue(parking.placeCarOnParking(car1));
        assertFalse(parking.placeCarOnParking(car2));
    }

    @Ignore
    @Test
    public void whenParkBigCarThenTrue() {
        Car car2 = new BigCar(4);
        Parking parking = new CityParking(0, 4);
        assertTrue(parking.placeCarOnParking(car2));
    }

    @Ignore
    @Test
    public void whenTooBigCarThenFalse() {
        Car car = new BigCar(4);
        Parking parking = new CityParking(0, 3);
        assertFalse(parking.placeCarOnParking(car));
    }

}