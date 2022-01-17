package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Element firstElement;

    public Menu(Element firstElement) {
        this.firstElement = firstElement;
    }

    public boolean add(String patternsName, String childName, Action action) {
        boolean result = false;
        Element element = findElement(firstElement, patternsName);
        if (element != null) {
            result = element.getInners().add(new MultiElem(childName, action));
        }
        return result;
    }

    Action select(String itemName) {
        Element element = findElement(firstElement, itemName);
        return element.getAction();
    }

    String getAsString() {
        StringBuilder sb = new StringBuilder();
        for (Element element : firstElement.getInners()) {
            sb.append(element.getName());
        }
        return sb.toString();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            this.showMenu(firstElement.getInners());
            System.out.println("Для выхода введите \"exit\"");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("exit")) {
                scanner.close();
                return;
            }
            Element element = findElement(firstElement, answer);
            if (element != null) {
                element.execute();
            }
        }
    }

    private void showMenu(List<Element> elements) {
        if (elements.equals(firstElement.getInners())) {
            System.out.println("Menu.");
        }
        for (Element element : elements) {
            System.out.println(element.getName());
            showMenu(element.getInners());
        }
    }

    public Element findElement(Element startElement, String name) {
        if (name.equalsIgnoreCase(startElement.getName())) {
            return startElement;
        } else {
            List<Element> list = startElement.getInners();
            if (list != null) {
                for (Element elements : list) {
                    return findElement(elements, name);
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Element el1 = new MultiElem("1", new PrintAction());
        Element el2 = new MultiElem("2", new CodeAction());
        el2.add(new MultiElem("2.1", new PrintAction()));
        el2.add(new MultiElem("2.2", new PrintAction()));
        Element el3 = new MultiElem("3", new PrintAction());
        Element el4 = new MultiElem("4", new PrintAction());
        Element el5 = new MultiElem("5", new PrintAction());
        Element el0 = new MultiElem("0", new PrintAction());
        el0.add(el1);
        el0.add(el2);
        el0.add(el3);
        el0.add(el4);
        el0.add(el5);
        Menu menu = new Menu(el0);
        menu.start();
    }
}
