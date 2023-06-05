package ru.itmo.jdbs;

import ru.itmo.cats.Category;

import java.sql.*;

public class JDBSLesson {
    private static final String CONNECTION_STR = "jdbc:postgresql://localhost:5432/lessons";
    private static final String LOGIN = "jjd";
    private static final String PWD = "itmo";
    public static void main(String[] args) {
        // jdbc
        // JDBC Driver Manager
        // Connection
        // Statement -> PreparedStatement
        // ResultSet
        createTable();
    }

    private static void createTable(){
        String createSql = "CREATE TABLE IF NOT EXISTS tb_categories (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "description VARCHAR(200) NOT NULL" +
                ");";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(CONNECTION_STR, LOGIN, PWD)){
            try (Statement statement = connection.createStatement()){
                System.out.println(statement.executeUpdate(createSql));
            } catch (SQLException e) {
                System.out.println("Не удалось выполнить запрос " + e.getMessage());
                System.out.println("Не удалось выполнить запрос " + e.getSQLState());
                System.out.println("Не удалось выполнить запрос " + e.getErrorCode());
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения " + e.getMessage());
            System.out.println("Ошибка подключения " + e.getSQLState());
            System.out.println("Ошибка подключения " + e.getErrorCode());
        }
    }

    private static void insert(Category category) {
        String insertString = "INSERT INTO tb_categories" +
                "(name, description) VALUES (?, ?)";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(CONNECTION_STR, LOGIN, PWD)){
            try (PreparedStatement prepared = connection.prepareStatement(insertString)){
                prepared.setString(1, category.getName());
                prepared.setString(2, category.getDescription());
                System.out.println(prepared.executeUpdate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






}
