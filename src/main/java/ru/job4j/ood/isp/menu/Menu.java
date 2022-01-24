package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Menu {
    private List<Element> list = new ArrayList<>();

    private Element checkAll(List<Element> list, Predicate<Element> predicate) {
        Element result = null;
        for (Element element : list) {
            if (predicate.test(element)) {
                result = element;
            }
            if (result != null) {
                break;
            }
            result = checkAll(element.getInners(), predicate);
        }
        return result;
    }

    public Element findElement(String name) {
        return checkAll(list, el -> name.equals(el.getName()));
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

    private void fillBuilder(List<Element> list, StringBuilder sb) {
        for (Element element : list) {
            sb.append(element.getName()).append("\n");
            fillBuilder(element.getInners(), sb);
        }
    }

    public String getAsString() {
        StringBuilder sb = new StringBuilder();
        fillBuilder(list, sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.add(null, "1", new PrintAction());
        menu.add(null, "2", new CodeAction());
        menu.add("2", "2.1", new PrintAction());
        menu.add("2.1", "2.1.2", new PrintAction());
        menu.add("2.1.2", "2.1.2.1", new PrintAction());
        menu.add("1", "1.3", new CodeAction());
        menu.add(null, "3", new CodeAction());
        menu.add("3", "3.1", new PrintAction());
        menu.add("3", "3.2", new PrintAction());
        System.out.println(menu.findElement("3.1").getName());
        System.out.println();
        System.out.println(menu.getAsString());
    }
}
