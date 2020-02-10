package DAO;

import java.time.LocalDate;

public abstract class DaoFactory {

    public abstract void createTable();
    public abstract void getData();
    public abstract void deleteData(LocalDate date);


}