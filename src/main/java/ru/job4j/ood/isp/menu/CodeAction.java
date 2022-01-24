package ru.job4j.ood.isp.menu;

public class CodeAction implements Action {

    @Override
    public void execute(String name) {
        System.out.println(
                "Выполняется метод execute() объекта CodeAction с именем: " + name
                        + System.lineSeparator()
        );
    }
}
