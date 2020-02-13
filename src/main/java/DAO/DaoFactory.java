package DAO;

import MAIN.Vacancy;

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
    private static  InputStream is;
    private static final Properties PROPERTIES = new Properties();
    static String CREATE_DB_VACANCIES;
    static String CREATE_DB_SKILLS;


    // FIXME они = null по дефолту, незачем писать лишний/ненужный код
    // FIXME зачем это здесь, если не используем?
    Connection connection=null;
    PreparedStatement statement=null;
    ResultSet resultSet=null;



    static {
        try {
            is = new FileInputStream("src/main/resources/properties.config");
            PROPERTIES.load(is);
            USER = PROPERTIES.getProperty("DB_USER");
            PASSWORD = PROPERTIES.getProperty("DB_PASSWORD");
            URL=PROPERTIES.getProperty("DB_URL");
            String DRIVER = PROPERTIES.getProperty("DB_DRIVER");
            CREATE_DB_VACANCIES=PROPERTIES.getProperty("CREATE_DB_VACANCIES");
            CREATE_DB_SKILLS=PROPERTIES.getProperty("CREATE_DB_SKILLS");
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
    static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        connection.setAutoCommit(false);
        return connection;

    }


}
