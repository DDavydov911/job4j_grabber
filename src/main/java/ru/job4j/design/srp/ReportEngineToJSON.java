package ru.job4j.design.srp;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReportEngineToJSON implements Report {

    private Store store;

    public ReportEngineToJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(list);
    }

    public static void main(String[] args) {
        MemStore ms = new MemStore();
        Calendar now = Calendar.getInstance();
        ms.add(new Employee("Ivan", now, now, 90));
        ms.add(new Employee("Semen", now, now, 70));
        ms.add(new Employee("Pavel", now, now, 100));
        ReportEngineToJSON rep = new ReportEngineToJSON(ms);
        System.out.println(rep.generate(el -> true));
    }
}