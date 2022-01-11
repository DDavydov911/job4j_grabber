package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CityParking implements Parking {
    private int freeSmallPlaces;
    private int freeBigPlaces;
    private final List<Car> carsOnSmallParkingPlaces = new ArrayList<>();
    private final List<Car> carsOnBigParkingPlaces = new ArrayList<>();

    public CityParking(int numberFreeSmallParkingPlaces, int numberFreeBigParkingPlaces) {
        this.freeSmallPlaces = numberFreeSmallParkingPlaces;
        this.freeBigPlaces = numberFreeBigParkingPlaces;
    }

    @Override
    public boolean placeCarOnParking(Car car) {
        if (car.getSize() == 1) {
            return placeSmallCar(car);
        } else {
            return placeBigCar(car);
        }
    }

    private boolean placeSmallCar(Car car) {
        int carSize = car.getSize();
        if (freeSmallPlaces >= carSize) {
            freeSmallPlaces -= carSize;
            carsOnSmallParkingPlaces.add(car);
            System.out.printf("Машина запаркована на легковой площадке,"
                    + " осталось свободными %d машиномест на  легковой площадке и "
                            + "%d машиномест на грузовой%n",
                    freeSmallPlaces, freeBigPlaces);
            return true;
        } else {
            System.out.println("Парковка занята");
            return false;
        }
    }

    private boolean placeBigCar(Car car) {
        if (freeBigPlaces > 0) {
            freeBigPlaces -= 1;
            carsOnBigParkingPlaces.add(car);
            System.out.printf("Машина запаркована на грузовой площадке,"
                            + " осталось свободными %d машиномест на  легковой площадке и "
                            + "%d машиномест на грузовой%n",
                    freeSmallPlaces, freeBigPlaces);
            return true;
        } else {
            return placeSmallCar(car);
        }
    }
}
