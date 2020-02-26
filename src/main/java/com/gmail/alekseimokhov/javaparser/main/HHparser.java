package com.gmail.alekseimokhov.javaparser.main;
import com.gmail.alekseimokhov.javaparser.dao.Vacancy_CRUD;

public class HHparser {

    public static void main(String[] args) {
        Vacancy_CRUD vacancy_crud = new Vacancy_CRUD();
        vacancy_crud.createTable();
       parse();
       read();
    }
    public static void parse(){
        Parser parser = new Parser();
        parser.work();
    }
    public static void read(){
        Vacancy_CRUD vacancy_crud= new Vacancy_CRUD();
        vacancy_crud.printData();
    }


}