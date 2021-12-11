package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean goon = true;
        System.out.println("Введите имя кэшируемой директории");
        String dir = scanner.nextLine();
        AbstractCache<String, String> cache = new DirFileCache(dir);
        String name;
        while (goon) {
            System.out.println("Если хотите выйти, наберите 'Q'");
            System.out.println("Если хотите кешировать файл, наберите 'С'");
            System.out.println("Если хотите прочитать кешироваый файл, наберите 'R'");
            name = scanner.nextLine();
            switch (name) {
                case "Q" :
                    goon = false;
                    break;
                case "C" :
                    System.out.println("Введите имя кэшируемого файла");
                    name = scanner.nextLine();
                    cache.load(name);
                    break;
                case "R" :
                    System.out.println("Введите имя читаемого файла");
                    name = scanner.nextLine();
                    System.out.println(cache.get(name));
                    break;
                default:
                    System.out.println("Введире правильный запрос");
                    name = scanner.nextLine();
            }
        }
    }
}
