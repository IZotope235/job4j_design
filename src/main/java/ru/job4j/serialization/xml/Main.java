package ru.job4j.serialization.xml;

import ru.job4j.serialization.java.Contact;
import ru.job4j.serialization.json.Customer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main  {
    public static void main(String[] args) throws Exception {
        Customer customer = new Customer(true, 345354345, "Ivan Ivanov",
                new Contact(7657654, "+7234234"), new String[]{"fish", "peanuts", "vine"});
        JAXBContext context = JAXBContext.newInstance(Customer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(customer, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Customer result = (Customer) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
