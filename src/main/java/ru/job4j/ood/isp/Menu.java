package ru.job4j.ood.isp;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Menu {

    public void start(Elements el) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            checkEls(el, e -> true, e -> System.out.println(e.getName()));
            System.out.println("Для выхода введите \"exit\"");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("exit")) {
                scanner.close();
                return;
            }
            checkEls(el, e -> e.getName().equals(answer.trim()), Elements::execute);
        }
    }

    public void checkEls(
            Elements el, Predicate<Elements> predicate, Consumer<Elements> elementsConsumer
    ) {
        if (predicate.test(el)) {
            elementsConsumer.accept(el);
        }
        List<Elements> list = el.getInners();
        if (list != null) {
            for (Elements elements : list) {
                checkEls(elements, predicate, elementsConsumer);
            }
        }
    }

    public static void main(String[] args) {
        Elements multiStart = new MultiElem("\n\nВыберите и введите номер пункта меню:");
        Elements multi1 = new MultiElem("1");
        Elements single11 = new SingleElem("1.1");
        Elements single12 = new SingleElem("1.2");
        multi1.add(single11);
        multi1.add(single12);
        Elements multi2 = new MultiElem("2");
        Elements single21 = new SingleElem("2.1");
        Elements multi22 = new MultiElem("2.2");
        Elements single221 = new SingleElem("2.2.1");
        Elements single222 = new SingleElem("2.2.2");
        multi22.add(single221);
        multi22.add(single222);
        multi2.add(single21);
        multi2.add(multi22);
        Elements single3 = new SingleElem("3");
        multiStart.add(multi1);
        multiStart.add(multi2);
        multiStart.add(single3);
        Menu menu = new Menu();
        menu.start(multiStart);
    }
}
