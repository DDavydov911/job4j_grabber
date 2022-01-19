package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Menu {
    private List<Element> list;
    private Element transit;
    private StringBuilder sb = new StringBuilder();

    public Menu(List<Element> list) {
        this.list = list;
    }

    public Element getTransit() {
        return transit;
    }

    public void checkAll(List<Element> list, Predicate<Element> predicate) {
        for (Element element : list) {
            if (predicate.test(element)) {
                transit = element;
            } else {
                checkAll(element.getInners(), predicate);
            }
        }
    }

    public Element findElement(String name) {
        checkAll(list, el -> name.equals(el.getName()));
        Element result = transit;
        transit = null;
        return result;
    }

    public boolean add(String patternsName, String childName, Action action) {
        boolean result = false;
        if (patternsName == null) {
            result = list.add(new MultiElem(childName, action));
        } else {
            Element element = findElement(patternsName);
            if (element != null) {
                result = element.getInners().add(new MultiElem(childName, action));
            }
        }
        return result;
    }

    public Action select(String name) {
        Element element = findElement(name);
        return (element != null) ? element.getAction() : null;
    }

    public void fillBuilder(List<Element> list) {
        for (Element element : list) {
            sb.append(element.getName()).append("\n");
            fillBuilder(element.getInners());
        }
    }

    public String getAsString() {
        fillBuilder(list);
        String result = sb.toString();
        sb = new StringBuilder();
        return result;
    }

    public static void main(String[] args) {
        List<Element> list = new ArrayList<>();
        Element el1 = new MultiElem("1", new PrintAction());
        Element el2 = new MultiElem("2", new CodeAction());
        list.add(el1);
        list.add(el2);
        Element el21 = new MultiElem("2.1", new PrintAction());
        el2.add(el21);
        Element el212 = new MultiElem("2.1.2", new PrintAction());
        el21.add(el212);
        Element el2121 = new MultiElem("2.1.2.1", new PrintAction());
        el212.add(el2121);
        Element el13 = new MultiElem("1.3", new CodeAction());
        el1.add(el13);
        Menu menu2 = new Menu(list);
        Element el3 = new MultiElem("3", new CodeAction());
        list.add(el3);
        System.out.println(menu2.add("3", "3.1", new PrintAction()));
        menu2.checkAll(list, el -> "3.1".equals(el.getName()));
        System.out.println(menu2.getAsString());
    }
}
