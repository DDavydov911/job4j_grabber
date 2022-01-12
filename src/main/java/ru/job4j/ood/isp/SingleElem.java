package ru.job4j.ood.isp;

import java.util.List;

public class SingleElem implements Elements {
    private String name;

    public SingleElem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(Elements element) {
    }

    @Override
    public List<Elements> getInners() {
        return null;
    }

    @Override
    public void execute() {
        System.out.printf("%nКод элемента %s выполняется", name);
    }
}
