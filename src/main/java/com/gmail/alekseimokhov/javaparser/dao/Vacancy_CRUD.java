package com.gmail.alekseimokhov.javaparser.dao;
import com.gmail.alekseimokhov.javaparser.main.Vacancy;
import java.sql.*;
import java.time.LocalDate;
public class Vacancy_CRUD extends DaoFactory {


    @Override
    public void createTable() {
        try(Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement(CREATE_DB_VACANCIES)){
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        }


    }

    @Override
    public void printData() {
        try(Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement("select*from vacancies")){
            resultSet =statement.executeQuery();
            while(resultSet.next()){
                System.out.print("{");
                System.out.print("ID: "+ resultSet.getInt(1)+ " ;");
                System.out.print(" Salary: "+ resultSet.getInt(2)+ " ;");
                System.out.print(" Experience: "+resultSet.getString(3)+ " ;");
                System.out.print(" Company : "+resultSet.getString(4)+ " ;");
                System.out.print(" City: "+resultSet.getString(5)+ " ;");
                System.out.print(" URL: "+resultSet.getString(6)+ " ;");
                System.out.print(" Date: "+resultSet.getDate(7)+ " ;");
                System.out.print("}");
                System.out.println();

            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        }
    }

    public void printData(int salary) {
        try(Connection connection=getConnection();
            PreparedStatement  statement =
                    connection.prepareStatement("select*from vacancies where salary >= "+salary);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()){
                System.out.println(resultSet.next());
                connection.commit();
            }
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();

        }
    }
    @Override
    public void deleteData(LocalDate date) {
        try(Connection connection=getConnection();
            PreparedStatement  statement =
                    connection.prepareStatement("delete from vacancies where"+date +" - postingDate >3")) {
            statement.executeQuery();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void persistData(Vacancy vacancy){
        try (Connection connection=getConnection();
             PreparedStatement  statement =
                     connection.prepareStatement("MERGE INTO vacancies KEY(url) VALUES(default,?,?,?,?,?,?) " )){
            statement.setInt(1,vacancy.getSalary());
            statement.setInt(2,vacancy.getExperience());
            statement.setString(3,vacancy.getCompany());
            statement.setString(4,vacancy.getCity());
            statement.setString(5,vacancy.getURL());
            statement.setDate(6,vacancy.getDate());
            statement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Existing vacancy");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}