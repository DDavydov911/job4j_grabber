package ru.job4j.design.srp;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngineForHR implements Report {

    private Store store;

    public ReportEngineForHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        list.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        StringBuilder text = new StringBuilder();
        text.append("Name;Salary;")
                .append(System.lineSeparator());
        for (Employee employee : list) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        MemStore ms = new MemStore();
        Calendar now = Calendar.getInstance();
        ms.add(new Employee("Ivan", now, now, 90));
        ms.add(new Employee("Semen", now, now, 70));
        ms.add(new Employee("Pavel", now, now, 100));
        ReportEngineForHR r = new ReportEngineForHR(ms);
        List<Employee> list = r.store.findBy(el -> true);
        for (Employee employee : list) {
            System.out.println(employee.getSalary());
        }
        list.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        for (Employee employee : list) {
            System.out.println(employee.getSalary());
        }
        System.out.println(r.generate(el -> true));
    }
}
