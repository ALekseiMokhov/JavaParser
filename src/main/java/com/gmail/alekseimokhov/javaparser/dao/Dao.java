package com.gmail.alekseimokhov.javaparser.dao;
import com.gmail.alekseimokhov.javaparser.main.Vacancy;
import java.time.LocalDate;

public interface Dao<E> {
     void createTable();
     void printData();
     void deleteData(LocalDate date);
     void persistData(Vacancy v);

}