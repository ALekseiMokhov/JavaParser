package MAIN;
import DAO.Vacancy_CRUD;
import WEB.WebParser;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainParser {

    public static void main(String[] args) {
      parse();
    }
    public static void parse(){
        WebParser webparser = new WebParser();
        webparser.work();
    }
    public static void read(){
        Vacancy_CRUD vacancy_crud= new Vacancy_CRUD();
        vacancy_crud.getData();
    }
    public static void loadVacancies(List<Vacancy> list){
        Vacancy_CRUD vacancie_persistance = new Vacancy_CRUD();
        for (Vacancy vacancy : list) {
            vacancie_persistance.mapVacancy(vacancy);
            System.out.println(vacancy.getSalary());
        }
    }

}