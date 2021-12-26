package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportEngineForProgrammers implements Report {
    private Store store;

    public ReportEngineForProgrammers(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>")
            .append(System.lineSeparator())
            .append("<html lang='ru'>")
            .append(System.lineSeparator())
            .append("<head>")
            .append(System.lineSeparator())
            .append("<meta charset='UTF-8'>")
            .append(System.lineSeparator())
            .append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>")
            .append(System.lineSeparator())
            .append("<title>Document</title>")
            .append(System.lineSeparator())
            .append("</head>")
            .append(System.lineSeparator())
            .append("<body>")
            .append(System.lineSeparator())
            .append("<p> Name; Hired; Fired; Salary; </p>")
            .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<p>")
                .append(employee.getName()).append(";")
                .append(employee.getHired()).append(";")
                .append(employee.getFired()).append(";")
                .append(employee.getSalary()).append(";")
                .append("</p>")
                .append(System.lineSeparator());
        }
        text.append("</body>")
            .append("</html>");
        return text.toString();
    }
}
