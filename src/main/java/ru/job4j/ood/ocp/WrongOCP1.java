package ru.job4j.ood.ocp;

public class WrongOCP1 {

    public class Figure {
        private double hight;
        private double width;

        public double getArea() {
            return hight * width;
        }
    }

    public class Triangle extends Figure {
        private double hight;
        private double width;

        @Override
        public double getArea() {
            return hight / 2 * width;
        }
    }

    public class Circle extends Figure {
        private double radius;

        @Override
        public double getArea() {
        return Math.PI * radius * radius;
        }
    }
}
