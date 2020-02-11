package DAO;
import MAIN.Vacancy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class Vacancy_CRUD implements DaoFactory {

    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static Properties PROPERTIES= new Properties();
    private static String CREATE_DB_VACANCIES;
    private static String CREATE_DB_SKILLS;

    Connection connection=null;
    PreparedStatement statement=null;
    ResultSet resultSet=null;
    InputStream is=null;

    {
        try {
            is = new FileInputStream("src/main/resources/properties.config");
            PROPERTIES.load(is);
            USER = PROPERTIES.getProperty("DB_USER");
            PASSWORD = PROPERTIES.getProperty("DB_PASSWORD");
            URL=PROPERTIES.getProperty("DB_URL");
            String DRIVER = PROPERTIES.getProperty("DB_DRIVER");
            CREATE_DB_VACANCIES=PROPERTIES.getProperty("CREATE_SQL");
            CREATE_DB_SKILLS=PROPERTIES.getProperty("CREATE_SQL2");
            Class.forName(DRIVER);
            try{
                is.close();
            }
            catch(IOException e){
                System.out.println("Can't close inputstream!");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File properties.config hasn't been found! ");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Properties can't find your data!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }
    protected static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        connection.setAutoCommit(false);
            return connection;

    }

    @Override
    public void createTable() {
        try{
            connection=getConnection();
            statement=connection.prepareStatement(CREATE_DB_VACANCIES);
            statement=connection.prepareStatement(CREATE_DB_SKILLS);
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
                System.out.print(" Expirience: "+resultSet.getString(3)+ " ;");
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

    public void getData(int salary) {

        try {
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
    @Override
    public void deleteData(LocalDate date) {
        try {
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
    public void mapVacancy(Vacancy vacancy){
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
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Existing vacancie");
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