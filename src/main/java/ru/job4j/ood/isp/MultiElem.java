package ru.job4j.ood.isp;

import java.util.ArrayList;
import java.util.List;

public class MultiElem implements Elements {
    private String name;
    private List<Elements> list = new ArrayList<>();

    public MultiElem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(Elements element) {
        list.add(element);
    }

    @Override
    public List<Elements> getInners() {
        return list;
    }

    @Override
    public void execute() {
        System.out.printf("Код элемента %s выполняется", name);
        /* Если хотим, чтобы код дочерних элементов тоже выполнялся, то раскомментируем
            if (!list.isEmpty()) {
                for (Elements menu : list) {
                    menu.execute();
                }
            }
         */
    }
}
