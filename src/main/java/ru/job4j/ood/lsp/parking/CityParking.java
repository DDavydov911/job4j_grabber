package ru.job4j.ood.lsp.parking;

public class CityParking implements Parking {
    private int smallCarParkingPlaces;
    private int bigCarParkingPlaces;

    public CityParking(int smallCarParkingPlaces, int bigCarParkingPlaces) {
        this.smallCarParkingPlaces = smallCarParkingPlaces;
        this.bigCarParkingPlaces = bigCarParkingPlaces;
    }

    @Override
    public void setCar(Car car) {
        if (car.getSize() == 1 && checkFreePlaces(car)) {
            smallCarParkingPlaces -= 1;
            System.out.println("Машина запаркована");
        } else if (checkFreePlaces(car)) {
            bigCarParkingPlaces -= car.getSize();
            System.out.println("Машина запаркована");
        } else {
            System.out.println("Парковка занята");
        }
    }

    @Override
    public boolean checkFreePlaces(Car car) {
         return car.getSize() <= 1 && 1 <= smallCarParkingPlaces
                 || car.getSize() > 1 && (car.getSize() <= bigCarParkingPlaces
                    || car.getSize() <= smallCarParkingPlaces);
    }

    public static void main(String[] args) {
        SmallCar sc1 = new SmallCar(1);
        SmallCar sc2 = new SmallCar(1);
        SmallCar sc3 = new SmallCar(1);
        SmallCar sc4 = new SmallCar(1);
        BigCar bc1 = new BigCar(4);
        BigCar bc2 = new BigCar(2);
        CityParking cityParking = new CityParking(3, 6);
        sc1.takeParking(cityParking);
        sc2.takeParking(cityParking);
        sc3.takeParking(cityParking);
        sc4.takeParking(cityParking);
        bc1.takeParking(cityParking);
        bc2.takeParking(cityParking);
    }
}
