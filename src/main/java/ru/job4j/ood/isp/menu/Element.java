package ru.job4j.ood.isp.menu;

import java.util.List;

public interface Element {

    String getName();

    Action getAction();

    boolean add(Element element);

    List<Element> getInners();

    void execute();
}
