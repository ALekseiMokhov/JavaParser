import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Dao extends DaoFactory {

    private static String URL;
    private static String HOST;
    private static String USER;
    private static String DRIVER;
    private static  String PASSWORD;
    private static Properties PROPERTIES= new Properties();

    private static InputStream is=null;
    private Connection connection=null;
    private PreparedStatement statement=null;


    public static void main(String[] args) {

        try{
            is = new FileInputStream("resources/properties.config");
            PROPERTIES.load(is);
            URL=PROPERTIES.getProperty("MYSQL_URL");
            HOST=PROPERTIES.getProperty("MYSQL_HOST");
            USER=PROPERTIES.getProperty("MYSQL_USER");
            DRIVER=PROPERTIES.getProperty("MYSQL_DRIVER");
            PASSWORD=PROPERTIES.getProperty("MYSQL_PASSWORD");
            Class.forName(DRIVER);

        } catch (FileNotFoundException e) {
            System.out.println("File properties.config hasn't been found! ");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Properties can't find your data!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }


    @Override
    public void createTable(String name) {
        try{
            Class.forName(DRIVER);
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            statement=connection.prepareStatement("create table ? (id INT PRIMARY KEY NOT NULL AUTOINCREMENT)");
            statement.setString(1,name);
            statement.executeQuery();
        } catch (ClassNotFoundException e) {
            System.out.println("");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        }
        finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getData() {
        try{
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            statement=connection.prepareStatement("select*from vacancies");
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        }
        finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public void getData(String s) {

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement("select*from vacancies "+s);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getInt("company"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deleteData(String s) {
        try {
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            statement=connection.prepareStatement("delete from vacancies where"+s);
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        }
        finally {
            try{
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
