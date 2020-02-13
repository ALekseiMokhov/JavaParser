package DAO;
import MAIN.Vacancy;
import java.sql.*;
import java.time.LocalDate;
public class Vacancy_CRUD extends DaoFactory {


    @Override
    public void createTable() {
        try{// FIXME лучше использовать try-with-resources
            connection=getConnection();
            statement=connection.prepareStatement(CREATE_DB_VACANCIES);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        }
        finally {
            if(statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    // FIXME метод ничего не возвращает и выглядит, как printData()
    @Override
    public void getData() {
        try{
            connection=getConnection();
            statement=connection.prepareStatement("select*from vacancies");
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
        finally {
            if(resultSet!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    // FIXME метод ничего не возвращает и выглядит, как printSalariesGreaterThan(int salary)
    public void getData(int salary) {

        try {// FIXME лучше использовать try-with-resources
            connection = getConnection();
            statement = connection.prepareStatement("select*from vacancies where salary >= "+salary);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.next());
                connection.commit();
            }
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();

        }
        finally {
            if(resultSet!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // FIXME опять несоотв. имя метода
    @Override
    public void deleteData(LocalDate date) {
        try {// FIXME лучше использовать try-with-resources
            connection=getConnection();
            statement=connection.
                    prepareStatement("delete from vacancies where"+date +" -postingDate >3");
            statement.executeQuery();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    // FIXME опять несоотв. имя метода
    public void persistData(Vacancy vacancy){
        try {
            connection = getConnection();
            statement=connection.
                    prepareStatement("INSERT INTO vacancies VALUES(default,?,?,?,?,?,?) " +
                            "ON CONFLICT DO NOTHING");
            statement.setInt(1,vacancy.getSalary());
            statement.setInt(2,vacancy.getExperience());
            statement.setString(3,vacancy.getCompany());
            statement.setString(4,vacancy.getCity());
            statement.setString(5,vacancy.getURL());
            statement.setDate(6,vacancy.getDate());
            statement.executeUpdate();

            // FIXME закоменченный код не должен находится в мастере

       /*
         statement=connection.prepareStatement("select * from skills limit 1");
            ResultSetMetaData resultSetMetaData =resultSet.getMetaData();
            statement=connection.prepareStatement("INSERT INTO skills values(" +
                    "default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            int countColumn = resultSetMetaData.getColumnCount();
            for (int i = 2; i < countColumn; i++ ) {
                String name = resultSetMetaData.getColumnName(i);
                System.out.println(name.toUpperCase());

                if(vacancy.getSkillsRequired().get(name.toUpperCase()) == true){
                    statement.setBoolean(i, true);
                } else {
                    statement.setBoolean(i, false);
                }
            }
            System.out.println(vacancy.getSkillsRequired());
            statement.executeUpdate();*/
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
        finally {
            if(statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}