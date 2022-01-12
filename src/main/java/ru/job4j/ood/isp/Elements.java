package ru.job4j.ood.isp;

import java.util.List;

public interface Elements {

    String getName();

    void add(Elements element);

    List<Elements> getInners();

    void execute();
}
