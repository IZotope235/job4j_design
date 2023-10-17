package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    @XmlAttribute
    private boolean active;
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String name;
    private Contact contact;
    @XmlElementWrapper(name = "topCategories")
    @XmlElement(name = "topCategory")
    private String[] topCategories;

    public Customer() {

    }

    public Customer(boolean active, int id, String name, Contact contact, String[] topCategories) {
        this.active = active;
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.topCategories = topCategories;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "active=" + active
                + ", id=" + id
                + ", name='" + name + '\''
                + ", contact=" + contact
                + ", topCategories=" + Arrays.toString(topCategories)
                + '}';
    }

    public static void main(String[] args) {
        final Customer customer = new Customer(true, 3453453, "Ivan Ivanov",
                new Contact(45345, "+73453453"), new String[] {"fish", "vine", "cheese"});

        final Gson gson = new GsonBuilder().create();
        String jsonCustomer = gson.toJson(customer);
        System.out.println(jsonCustomer);

        final Customer customerFromJson = gson.fromJson(jsonCustomer, Customer.class);
        System.out.println(customerFromJson);


    }
}
