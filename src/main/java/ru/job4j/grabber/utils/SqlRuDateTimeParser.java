package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(Map.entry("янв", "01"),
        Map.entry("фев", "02"), Map.entry("мар", "03") , Map.entry("апр", "04"),
        Map.entry("май", "05"), Map.entry("июн", "06"), Map.entry("июл", "07"),
        Map.entry("авг", "08"), Map.entry("сен", "09"), Map.entry("окт", "10"),
        Map.entry("ноя", "11"), Map.entry("дек", "12"));

    @Override
    public LocalDateTime parse(String parse) {
        String[] arr = parse.split(" |, |:");
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MM yy HH mm");
        return LocalDateTime.parse(
                arr[0] + MONTHS.get(arr[1]) + arr[2] + arr[3] + arr[4], DATE_FORMAT
        );
    }
}