package ru.job4j.ood.ocp;

public class WrongOCP2 {

    public class Car {
        private int seats;
        private String engineType;
        private int power;
        private int maxSpeed;
        private double fuel;

        public void getFuel(double value) {
            fuel += value;
        }
    }

    public class GasCar extends Car {
        private int seats;
        private String engineType = "Gas";
        private int power;
        private int maxSpeed;
        private double fuel;

        public void getFuel(double value) {
            fuel += value;
        }
    }

    public class ECar extends Car {
        private int seats;
        private String engineType = "Electric";
        private String brake;
        private int power;
        private int maxSpeed;
        private  double charge;

        public void getCharge(double value) {
            charge += value;
        }
    }
}
