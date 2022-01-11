package ru.job4j.ood.lsp.parking;

import org.junit.Test;
import static org.junit.Assert.*;

public class CityParkingTest {

    @Test
    public void whenPark2SmallCarFor1PlaceThenTrueAndFalse() {
        Car car1 = new SmallCar();
        Car car2 = new SmallCar();
        Parking parking = new CityParking(1, 0);
        assertTrue(parking.placeCarOnParking(car1));
        assertFalse(parking.placeCarOnParking(car2));
    }

    @Test
    public void whenParkBigCarThenTrue() {
        Car car2 = new BigCar(4);
        Parking parking = new CityParking(0, 4);
        assertTrue(parking.placeCarOnParking(car2));
    }

    @Test
    public void when4BigCarsFor3PlacesThenFalse() {
        Car car1 = new BigCar(4);
        Car car2 = new BigCar(2);
        Car car3 = new BigCar(10);
        Car car4 = new BigCar(3);
        Parking parking = new CityParking(0, 3);
        parking.placeCarOnParking(car1);
        parking.placeCarOnParking(car2);
        parking.placeCarOnParking(car3);
        assertFalse(parking.placeCarOnParking(car4));
    }

    @Test
    public void when3BigCarsFor2BigPlacesAnd4SmallThenTrue() {
        Car car1 = new BigCar(4);
        Car car2 = new BigCar(2);
        Car car3 = new BigCar(4);
        Car car4 = new BigCar(3);
        Parking parking = new CityParking(3, 3);
        parking.placeCarOnParking(car1);
        parking.placeCarOnParking(car2);
        parking.placeCarOnParking(car3);
        assertTrue(parking.placeCarOnParking(car4));
    }

    @Test
    public void whenParkingIsBusyThenFalse() {
        Car car1 = new SmallCar();
        Car car2 = new SmallCar();
        Car car3 = new SmallCar();
        Car car4 = new BigCar(2);
        Car car5 = new BigCar(4);
        Car car6 = new BigCar(3);
        Parking parking = new CityParking(2, 2);
        parking.placeCarOnParking(car1);
        parking.placeCarOnParking(car2);
        parking.placeCarOnParking(car4);
        parking.placeCarOnParking(car5);
        assertFalse(parking.placeCarOnParking(car3));
        assertFalse(parking.placeCarOnParking(car6));
    }

}