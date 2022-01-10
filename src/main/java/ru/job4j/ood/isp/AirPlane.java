package ru.job4j.ood.isp;
/*
    Все самолеты взлетают и приземляются, но не все могут брать пассажиров.
 */
public interface AirPlane {

    void takeOff();

    void landing();

    void takePassengers();
}
