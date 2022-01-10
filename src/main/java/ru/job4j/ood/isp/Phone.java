package ru.job4j.ood.isp;
/*
    Все телефоны совершают звонки, но не на всех можно поставить приложения и загрузить их.
 */
public interface Phone {
    void makePhoneCall();

    void getApp();

    void loudApp();
}
