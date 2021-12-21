package ru.job4j.kiss;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    List<Integer> list = List.of(7, 2, 1, 8, 2);
    MaxMin maxMin = new MaxMin();

    @Test
    public void whenMaxIs8() {
        int res = maxMin.max(list, Integer::compareTo);
        assertThat(res, is(8));
    }

    @Test
    public void whenMinIs1() {
        int res = maxMin.min(list, Integer::compareTo);
        assertThat(res, is(1));
    }
}