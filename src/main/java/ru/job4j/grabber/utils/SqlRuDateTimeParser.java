package ru.job4j.grabber.utils;

import ru.job4j.grabber.Tester;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
        if (arr[0].equals("сегодня")) {
            ld = LocalDate.now();
            lt = LocalTime.parse(arr[1] + arr[2], dtf);
        } else if (arr[0].equals("вчера")) {
            ld = LocalDate.now().minusDays(1);
            lt = LocalTime.parse(arr[1] + arr[2], dtf);
        } else {
            ld = LocalDate.parse(arr[0] + MONTHS.get(arr[1]) + arr[2],
                    DateTimeFormatter.ofPattern("dMMyy"));
            lt = LocalTime.parse(arr[3] + arr[4], dtf);
        }
        return LocalDateTime.of(ld, lt);
    }
}