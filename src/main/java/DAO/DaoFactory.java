package DAO;

import java.time.LocalDate;

public interface DaoFactory {
     void createTable();
     void getData();
     void deleteData(LocalDate date);


}