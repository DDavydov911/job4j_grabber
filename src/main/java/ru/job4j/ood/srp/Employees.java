package ru.job4j.ood.srp;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
public class Employees {
    private List<Employee> list;

    public Employees() { }

    public Employees(List<Employee> list) {
        this.list = list;
    }

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }
}
