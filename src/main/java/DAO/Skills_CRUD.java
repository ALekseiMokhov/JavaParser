package DAO;

import MAIN.Desc;
import MAIN.Vacancy;

import java.sql.SQLException;
import java.time.LocalDate;
@Desc(text = "map skill and link with correct vacancie. ")

/*Need to add extra DAO layer with connection initialising, setting constants etc.*/
public class Skills_CRUD extends DaoFactory{
    @Override
    public void createTable() {
        try{
            // FIXME лучше использовать try-with-resources
            connection=getConnection();
            System.out.println("connection is successfull");
            statement=connection.prepareStatement(CREATE_DB_SKILLS);
            System.out.println("statement 's been prepared is successfull");
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

    }

    @Override
    public void deleteData(LocalDate date) {

    }

    @Override
    public void persistData(Vacancy element) {

    }


}
