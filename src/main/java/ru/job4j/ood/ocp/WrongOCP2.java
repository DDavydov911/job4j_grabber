package ru.job4j.ood.ocp;

public class WrongOCP2 {

    public class Car {
        private int seats;
        private String engineType;
        private String brake;
        private int power;
        private int maxSpeed;
    }

    public class GasCar extends Car {
        private int seats;
        private String engineType = "Gas";
        private String brake;
        private int power;
        private int maxSpeed;
    }

    public class GasCarWithABS extends Car {
        private int seats;
        private String engineType = "Gas";
        private String brake = "ABC brake";
        private int power;
        private int maxSpeed;
    }

    public class ECar extends Car {
        private int seats;
        private String engineType = "Electric";
        private String brake;
        private int power;
        private int maxSpeed;
    }

    public class EcarWithABS extends Car {
        private int seats;
        private String engineType = "Electric";
        private String brake = "ABC brake";
        private int power;
        private int maxSpeed;
    }
}
