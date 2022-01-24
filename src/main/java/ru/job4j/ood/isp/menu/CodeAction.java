package ru.job4j.ood.isp.menu;

public class CodeAction implements Action {

    @Override
    public void execute(String text) {
        System.out.println(
                "Выполняется метод execute() объекта CodeAction: " + text
                        + System.lineSeparator()
        );
    }
}
