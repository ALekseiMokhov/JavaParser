import com.gmail.alekseimokhov.javaparser.main.Vacancy;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Vacancy_CRUD_TEST {
    private static String URL;
    private static String USER;
    private static  String PASSWORD;
    private static Properties PROPERTIES= new Properties();

    Connection connection=null;
    PreparedStatement statement=null;
    ResultSet resultSet=null;
    InputStream is=null;


    {
        try {
            System.out.println("Trying to load props file..");
            is = new FileInputStream("src/main/resources/properties.config");
            PROPERTIES.load(is);
            USER = PROPERTIES.getProperty("DB_USER");
            PASSWORD = PROPERTIES.getProperty("DB_PASSWORD");
            URL=PROPERTIES.getProperty("DB_URL");
            String DRIVER = PROPERTIES.getProperty("DB_DRIVER");
            String CREATE_DB_VACANCIES = PROPERTIES.getProperty("CREATE_SQL");
            String CREATE_DB_SKILLS = PROPERTIES.getProperty("CREATE_SQL2");
            Class.forName(DRIVER);
            System.out.println(USER);
            System.out.println(PASSWORD);
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
    @Test
    public void mapVacancyTest(Vacancy vacancy){
        try {
            connection= DriverManager.getConnection(URL,USER,PASSWORD);
            statement=connection.
                    prepareStatement("INSERT INTO vacancies VALUES(?,?,?,?,?,?)");
            statement.setInt(1,vacancy.getID());
            statement.setString(2,vacancy.getCity());
            statement.setString(3,vacancy.getCompany());
            statement.setInt(4,vacancy.getExperience());
            statement.setDate(5,vacancy.getDate());
            statement.setInt(6,vacancy.getSalary());
            statement.setString(7,vacancy.getURL());
            statement.executeUpdate();
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
}
