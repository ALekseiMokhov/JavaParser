import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

class Dao_Postgres extends DaoFactory {

    private static String URL;
    private static String USER;
    private static String DRIVER;
    private static  String PASSWORD;
    private static Properties PROPERTIES= new Properties();

    Connection connection=null;
    PreparedStatement statement=null;
    ResultSet resultSet=null;
    InputStream is=null;


    {
        try {
            System.out.println("Trying to load props file..");
            is = new FileInputStream("resources/properties.config");
            PROPERTIES.load(is);
            USER = PROPERTIES.getProperty("DB_USER");
            PASSWORD = PROPERTIES.getProperty("DB_PASSWORD");
            URL=PROPERTIES.getProperty("DB_URL");
            DRIVER=PROPERTIES.getProperty("DB_DRIVER");
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

    @Override
    public void createTable(String name) {
        try{
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            statement=connection.prepareStatement("create table ? (id INT PRIMARY KEY NOT NULL AUTOINCREMENT)");
            statement.setString(1,name);
            statement.executeQuery();
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
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            statement=connection.prepareStatement("select*from vacancies");
            resultSet =statement.executeQuery();
            while(resultSet.next()){
                System.out.print(resultSet.getInt(1)+ " ");
                System.out.print(resultSet.getInt(2)+ " ");
                System.out.print(resultSet.getInt(3)+ " ");
                System.out.print(resultSet.getString(4)+ " ");
                System.out.print(resultSet.getString(5)+ " ");
                System.out.print(resultSet.getString(6)+ " ");
                System.out.println();

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

    public void getData(String s) {

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement("select*from vacancies "+s);
            resultSet = statement.executeQuery();
            while(resultSet.next()){

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
    public void deleteData(String s) {
        try {
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            statement=connection.prepareStatement("delete from vacancies where"+s);
            statement.executeQuery();
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