package com.gmail.alekseimokhov.javaparser.dao;

import com.gmail.alekseimokhov.javaparser.main.Vacancy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Skills_CRUD extends DaoFactory{
    @Override
    public void createTable() {
        try(Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement(CREATE_DB_SKILLS)){
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        }
    }

    @Override
    public void printData() {

    }


    @Override
    public void deleteData(LocalDate date) {

    }

    @Override
    public void persistData(Vacancy element) {

    }


}
