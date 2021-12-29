package ru.job4j.ood.ocp;

public class WrongOCP2 {
    /*
     Метод getFuel, определенный в родительском классе Car, учитывает только один вид топлива,
     В случае, если у нас топливо - электроэнергия, то это другие единицы измерения
     и другое название. В дочернем классе ECar создается другой метод, а это модификация.
     */
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
