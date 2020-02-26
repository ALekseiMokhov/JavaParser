package com.gmail.alekseimokhov.javaparser.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public abstract class DaoFactory implements Dao{

    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static final Properties PROPERTIES = new Properties();
    static String CREATE_DB_VACANCIES;
    static String CREATE_DB_SKILLS;

    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;

    static {
        try(InputStream inputStream = new FileInputStream("src/main/resources/properties.config")) {
            PROPERTIES.load(inputStream);
            USER = PROPERTIES.getProperty("DB_USER");
            PASSWORD = PROPERTIES.getProperty("DB_PASSWORD");
            URL=PROPERTIES.getProperty("DB_URL");
            String DRIVER = PROPERTIES.getProperty("DB_DRIVER");
            CREATE_DB_VACANCIES=PROPERTIES.getProperty("CREATE_DB_VACANCIES");
            CREATE_DB_SKILLS=PROPERTIES.getProperty("CREATE_DB_SKILLS");
            Class.forName(DRIVER);

    } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        connection.setAutoCommit(false);
        return connection;

    }


}
