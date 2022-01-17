package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class MultiElem implements Element {
    private String name;
    private List<Element> list = new ArrayList<>();
    private Action action;

    public MultiElem(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public boolean add(Element element) {
        return list.add(element);
    }

    @Override
    public List<Element> getInners() {
        return list;
    }

    @Override
    public void execute() {
        action.execute(name);
    }
}
