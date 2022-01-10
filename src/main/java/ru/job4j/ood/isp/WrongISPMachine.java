package ru.job4j.ood.isp;
/*
    Избыточное количемтво методов в интерфейсе.
    Для Кофемашины нужен только createCoffee, продукты он не выдает,
    а деньги принимают не все кофемашины.
    Вендингу нужны только методы принять деньги и выдать продукт,
    кофе вентинговый аппарат не варит.
 */
public interface WrongISPMachine {
    void takeMoney();

    void createCoffee();

    void giveFood();
}
