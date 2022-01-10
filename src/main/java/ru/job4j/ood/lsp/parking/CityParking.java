package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;

public class CityParking implements Parking {
    private int numberFreeSmallParkingPlaces;
    private int numberFreeBigParkingPlaces;
    private final ArrayList<Car> carsOnSmallParkingPlaces = new ArrayList<>();
    private final ArrayList<Car> carsOnBigParkingPlaces = new ArrayList<>();

    public CityParking(int numberFreeSmallParkingPlaces, int numberFreeBigParkingPlaces) {
        this.numberFreeSmallParkingPlaces = numberFreeSmallParkingPlaces;
        this.numberFreeBigParkingPlaces = numberFreeBigParkingPlaces;
    }

    @Override
    public boolean placeCarOnParking(Car car) {
        if (car.getSize() == 1) {
            return placeCarOnSmallParkingPlaces(car);
        } else {
            return placeBigCarOnParking(car);
        }
    }

    @Override
    public boolean contains(Car car) {
        return false;
    }

    private boolean placeCarOnSmallParkingPlaces(Car car) {
        int carSize = car.getSize();
        if (numberFreeSmallParkingPlaces >= carSize) {
            numberFreeSmallParkingPlaces -= carSize;
            carsOnSmallParkingPlaces.add(car);
            System.out.printf("Машина запаркована на легковой площадке,"
                    + " осталось свободными %d машиномест на  легковой площадке и "
                            + "%d машиномест на грузовой%n",
                    numberFreeSmallParkingPlaces, numberFreeBigParkingPlaces);
            return true;
        } else {
            System.out.println("Парковка занята");
            return false;
        }
    }

    private boolean placeBigCarOnParking(Car car) {
        int carSize = car.getSize();
        if (numberFreeBigParkingPlaces >= carSize) {
            numberFreeBigParkingPlaces -= carSize;
            carsOnBigParkingPlaces.add(car);
            System.out.printf("Машина запаркована на грузовой площадке,"
                            + " осталось свободными %d машиномест на  легковой площадке и "
                            + "%d машиномест на грузовой%n",
                    numberFreeSmallParkingPlaces, numberFreeBigParkingPlaces);
            return true;
        } else {
            return placeCarOnSmallParkingPlaces(car);
        }
    }
}
