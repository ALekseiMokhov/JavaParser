package DAO;
import MAIN.Vacancy;
import java.time.LocalDate;

public interface Dao<E> {
     void createTable();
     void getData();
     void deleteData(LocalDate date);
     void persistData(Vacancy v);

}