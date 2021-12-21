package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T comparing(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T res = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            int compare = comparator.compare(res, value.get(i));
            res = predicate.test(compare) ? value.get(i) : res;
        }
        return res;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return comparing(value, comparator, number -> number < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return comparing(value, comparator, number -> number > 0);
    }
}