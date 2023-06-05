package ru.itmo.jdbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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




}
