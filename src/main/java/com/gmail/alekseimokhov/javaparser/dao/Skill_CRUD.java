package com.gmail.alekseimokhov.javaparser.dao;
import com.gmail.alekseimokhov.javaparser.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Skill_CRUD extends DaoFactory<Skill>{
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
    public void printAllData() {
        try(Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement("select distinct name from skills"))
        {
            resultSet =statement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("name"));

            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        }
    }


    public void printAllVacancies(int id) {
        try(Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement("select name from skills where id=?")){
            statement.setInt(1,id);
            resultSet =statement.executeQuery();
            while(resultSet.next()){
                System.out.print(resultSet.getString("name"));

            }

        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        }
    }

    @Override
    public void saveData(Skill skill) {

        try (Connection connection=getConnection();
             PreparedStatement  statement =
                     connection.prepareStatement("INSERT INTO skill VALUES(default,?)" )){
            statement.setString(1,skill.getSkillName());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in skill persistence!");
        }
    }


}
