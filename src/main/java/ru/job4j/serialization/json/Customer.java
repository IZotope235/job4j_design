package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.java.Contact;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isActive() {
        return active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getTopCategories() {
        return topCategories;
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

        JSONObject jsonContact = new JSONObject("{\"zipCode\":45345, \"phone\":\"+73453435\"}");

        List<String> list = new ArrayList<>();
        list.add("fish");
        list.add("vine");
        list.add("cheese");
        JSONArray jsonTopCategoies = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("active", customer.isActive());
        jsonObject.put("id", customer.getId());
        jsonObject.put("name", customer.getName());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("topCategories", jsonTopCategoies);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(customer));
    }
}
