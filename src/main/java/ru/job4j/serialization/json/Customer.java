package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Customer {
    private final boolean active;
    private final int id;
    private final String name;
    private final Contact contact;
    private final String[] topCategories;

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
