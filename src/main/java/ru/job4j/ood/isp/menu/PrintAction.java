package ru.job4j.ood.isp.menu;

public class PrintAction implements Action {

    @Override
    public void execute(String name) {
        System.out.println(
                "Выполняется метод execute() объекта PrintAction с именем: " + name + "\n"
        );
    }

    @Override
    public String toString() {
        return "PrintAction{}";
    }
}
