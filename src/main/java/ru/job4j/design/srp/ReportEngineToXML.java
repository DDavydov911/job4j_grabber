package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngineToXML implements Report {
    private Store store;

    public ReportEngineToXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        JAXBContext context;
        Marshaller marshaller = null;
        try {
            context = JAXBContext.newInstance(Employee.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        String result = null;
        try (StringWriter writer = new StringWriter()) {
            for (Employee employee : list) {
                marshaller.marshal(employee, writer);
            }
            result = writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        MemStore ms = new MemStore();
        Calendar now = Calendar.getInstance();
        ms.add(new Employee("Ivan", now, now, 90));
        ms.add(new Employee("Semen", now, now, 70));
        ms.add(new Employee("Pavel", now, now, 100));
        ReportEngineToXML report = new ReportEngineToXML(ms);
        System.out.println(report.generate(el -> true));
    }
}
