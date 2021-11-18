package ru.job4j.grabber;

import ru.job4j.grabber.utils.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

public class Tester implements DateTimeParser {
    private static final Map<String, String> MONTHS = Map.ofEntries(Map.entry("янв", "01"),
            Map.entry("фев", "02"), Map.entry("мар", "03"), Map.entry("апр", "04"),
            Map.entry("май", "05"), Map.entry("июн", "06"), Map.entry("июл", "07"),
            Map.entry("авг", "08"), Map.entry("сен", "09"), Map.entry("окт", "10"),
            Map.entry("ноя", "11"), Map.entry("дек", "12"));

    @Override
    public LocalDateTime parse(String parse) {
        String[] arr = parse.split("\s+|,\s+|:");
        LocalDate ld;
        LocalTime lt;
        if (arr.length == 3) {
            lt = LocalTime.parse(arr[1] + arr[2], DateTimeFormatter.ofPattern("HHmm"));
            if (arr[0].equals("сегодня")) {
                ld = LocalDate.now();
            } else {
                ld = LocalDate.now().minusDays(1);
            }
        } else {
            ld = LocalDate.parse(arr[0] + MONTHS.get(arr[1]) + arr[2],
                    DateTimeFormatter.ofPattern("dMMyy"));
            lt = LocalTime.parse(arr[3] + arr[4], DateTimeFormatter.ofPattern("HHmm"));
        }
        return LocalDateTime.of(ld, lt);
    }

    public static void main(String[] args) {
        String str1 = "27 окт 21, 20:49";
        String str2 = "2 ноя 21, 09:00";
        String str3 = "сегодня, 13:24";
        String str4 = "     сегодня,     15:41     ";
        String str5 = "вчера, 15:20";
        String[] arr = str4.trim().split("\s+|,\s+|:");
        System.out.println(arr.length);
        for (String s : arr) {
            System.out.println(s);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
        Tester parser = new Tester();
        System.out.println(parser.parse(str1));
        System.out.println(parser.parse(str2));
        System.out.println(parser.parse(str3));
        System.out.println(parser.parse(str4));
        System.out.println(parser.parse(str5));
    }
}
