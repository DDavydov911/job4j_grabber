package ru.job4j.srp;
/*
Единственная задача класса хранить единственный экземпляр объекта,
но есть еще метод что-то сделать, что нарушает SRP
 */
public class WrongSRP2 {
    private WrongSRP2 instance;

    public WrongSRP2 getInstance() {
        if (instance == null) {
            instance = new WrongSRP2();
        }
        return instance;
    }

    public void doSmth() {
    }
}
