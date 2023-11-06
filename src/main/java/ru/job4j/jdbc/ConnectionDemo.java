package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import ru.job4j.io.Config;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config postgresConfig = new Config(".\\src\\main\\resources\\app.properties");
        postgresConfig.load();
        Class.forName(postgresConfig.value("driver_class"));
        String url = postgresConfig.value("url");
        String login = postgresConfig.value("login");
        String password = postgresConfig.value("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
