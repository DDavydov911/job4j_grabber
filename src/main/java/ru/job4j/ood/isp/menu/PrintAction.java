package ru.job4j.ood.isp.menu;

public class PrintAction implements Action {

    @Override
    public void execute(String text) {
        System.out.println(
                "Выполняется метод execute() объекта PrintAction: " + text
                        + System.lineSeparator()
        );
    }

    @Override
    public String toString() {
        return "PrintAction{}";
    }
}
