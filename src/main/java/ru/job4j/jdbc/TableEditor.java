package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException {
        this.properties = properties;
        initConnection();
    }

    private static Properties getProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);
        }
        return properties;
    }

    private void initConnection() throws SQLException {
        var url = properties.getProperty("url");
        var login = properties.getProperty("login");
        var password = properties.getProperty("password");
        this.connection = DriverManager.getConnection(url, login, password);
    }

    private void executeStatement(String sql) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s();", tableName
            );
        executeStatement(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s;", tableName
        );
        executeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s ADD COLUMN %s %s;", tableName, columnName, type
        );
        executeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s DROP COLUMN IF EXISTS %s RESTRICT;",
                tableName, columnName
        );
        executeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        executeStatement(sql);
    }

    public String getTableScheme(String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public void isTableExist(String tableName) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            String sql = String.format(
                    "SELECT EXISTS ("
                            + "SELECT FROM information_schema.tables "
                            + "WHERE table_schema LIKE 'public' AND "
                            + "table_type LIKE 'BASE TABLE' AND "
                            + "table_name = '%s');", tableName
            );
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            boolean result = resultSet.getBoolean(1);
            System.out.printf("Table \"%s\" : %s ", tableName, result ? "exist" : "does not exist");
        }
    }

    public static void main(String[] args) {
        try (TableEditor tableEditor = new TableEditor(getProperties("app.properties"))) {
            tableEditor.createTable("test");
            System.out.println(tableEditor.getTableScheme("test"));
            tableEditor.addColumn("test", "name", "varchar(30)");
            System.out.println(tableEditor.getTableScheme("test"));
            tableEditor.renameColumn("test", "name", "name1");
            System.out.println(tableEditor.getTableScheme("test"));
            tableEditor.dropColumn("test", "name1");
            System.out.println(tableEditor.getTableScheme("test"));
            tableEditor.dropTable("test");
            tableEditor.isTableExist("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
